package com.example.test;

import com.example.test.model.Customer;
import com.example.test.model.CustomerRequest;
import com.example.test.model.CustomerResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class CrudCliApplication implements CommandLineRunner {

	private static final String BASE_URL = "http://localhost:8080/api/customers"; // Your API base URL
	private static final String USERNAME = "dev"; // Replace with your actual username
	private static final String PASSWORD = "devPassword"; // Replace with your actual password

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationContext context;  // Injecting the ApplicationContext to shut down the application


	public static void main(String[] args) {
		SpringApplication.run(CrudCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("----- CRUD Operations -----");
			System.out.println("1. Get All Customers");
			System.out.println("2. Get Customer By ID");
			System.out.println("3. Create Customer");
			System.out.println("4. Update Customer");
			System.out.println("5. Delete Customer");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");
			choice = scanner.nextInt();
			scanner.nextLine();  // Consume the newline character

			switch (choice) {
				case 1:
					// Get All Customers
					try{
						HttpHeaders getHeaders = createHeadersWithBasicAuth();
						HttpEntity<Void> getRequestEntity = new HttpEntity<>(getHeaders);
						ResponseEntity<String> allCustomersResponse = restTemplate.exchange(BASE_URL, HttpMethod.GET, getRequestEntity, String.class);
						System.out.println("All Customers: ");
						System.out.println(allCustomersResponse.getBody());
					} catch (HttpClientErrorException | HttpServerErrorException e) {
						System.out.println("Error occurred while creating customer: " + e.getMessage());
					} catch (Exception e) {
						System.out.println("Unexpected error occurred: " + e.getMessage());
					}
					break;

				case 2:
					// Get Customer By ID
					try{
						System.out.print("Enter customer ID: ");
						Long id = scanner.nextLong();
						HttpHeaders getByIdHeaders = createHeadersWithBasicAuth();
						HttpEntity<Void> getByIdRequestEntity = new HttpEntity<>(getByIdHeaders);

						ResponseEntity<String> customerResponse = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.GET, getByIdRequestEntity, String.class);
						System.out.println("Customer: ");
						System.out.println(customerResponse.getBody());
					} catch (HttpClientErrorException | HttpServerErrorException e) {
						System.out.println("Error occurred while creating customer: " + e.getMessage());
					} catch (Exception e) {
						System.out.println("Unexpected error occurred: " + e.getMessage());
					}
					break;
				case 3:
					// Create Customer
					try {
						System.out.print("Enter first name: ");
						String firstName = scanner.nextLine();
						System.out.print("Enter middle name (leave blank if no middle name): ");
						String middleName = scanner.nextLine();
						System.out.print("Enter last name: ");
						String lastName = scanner.nextLine();
						System.out.print("Enter email: ");
						String email = scanner.nextLine();
						System.out.print("Enter phone number: ");
						String phone = scanner.nextLine();

						CustomerRequest newCustomer = new CustomerRequest();
						newCustomer.setFirstName(firstName);
						newCustomer.setMiddleName(middleName);
						newCustomer.setLastName(lastName);
						newCustomer.setEmailAddress(email);
						newCustomer.setPhoneNumber(phone);
						HttpHeaders headers = createHeadersWithBasicAuth();
						HttpEntity<CustomerRequest> requestEntity = new HttpEntity<>(newCustomer, headers);

						ResponseEntity<String> createResponse = restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, String.class);
						System.out.println("Response: " + createResponse.getBody());
					} catch (HttpClientErrorException | HttpServerErrorException e) {
						System.out.println("Error occurred while creating customer: " + e.getMessage());
					} catch (Exception e) {
						System.out.println("Unexpected error occurred: " + e.getMessage());
					}
					break;
				case 4:
					// Update Customer
					try{
						System.out.print("Enter customer ID to update: ");
						Long updateId = scanner.nextLong();
						scanner.nextLine();  // Consume newline
						System.out.print("Enter first name (leave blank to keep current): ");
						String newFirstName = scanner.nextLine();
						System.out.print("Enter middle name (leave blank to keep current): ");
						String newMiddleName = scanner.nextLine();
						System.out.print("Enter last name (leave blank to keep current): ");
						String newLastName = scanner.nextLine();
						System.out.print("Enter email (leave blank to keep current): ");
						String newEmail = scanner.nextLine();
						System.out.print("Enter phone number (leave blank to keep current): ");
						String newPhone = scanner.nextLine();
						CustomerRequest updateCustomer = new CustomerRequest();
						//if (!newFirstName.isEmpty()) {
							updateCustomer.setFirstName(newFirstName);
						//}
						updateCustomer.setMiddleName(newMiddleName);
						updateCustomer.setLastName(newLastName);
						updateCustomer.setEmailAddress(newEmail);
						updateCustomer.setPhoneNumber(newPhone);
						HttpHeaders updateHeaders = createHeadersWithBasicAuth();
						HttpEntity<CustomerRequest> updateRequestEntity = new HttpEntity<>(updateCustomer, updateHeaders);

						ResponseEntity<String> updateResponse = restTemplate.exchange(BASE_URL + "/" + updateId, HttpMethod.PUT, updateRequestEntity, String.class);
						System.out.println("Customer updated successfully." +updateResponse.getBody());
					} catch (HttpClientErrorException | HttpServerErrorException e) {
						System.out.println("Error occurred while creating customer: " + e.getMessage());
					} catch (Exception e) {
						System.out.println("Unexpected error occurred: " + e.getMessage());
					}
					break;

				case 5:
					// Delete Customer
					try {
						System.out.print("Enter customer ID to delete: ");
						Long deleteId = scanner.nextLong();
						HttpHeaders deleteHeaders = createHeadersWithBasicAuth();
						HttpEntity<Void> deleteRequestEntity = new HttpEntity<>(deleteHeaders);

						ResponseEntity<String> deleteResponse = restTemplate.exchange(BASE_URL + "/" + deleteId, HttpMethod.DELETE, deleteRequestEntity, String.class);
						System.out.println("Customer deleted successfully."+deleteResponse.getBody());
					} catch (HttpClientErrorException | HttpServerErrorException e) {
						System.out.println("Error occurred while creating customer: " + e.getMessage());
					} catch (Exception e) {
						System.out.println("Unexpected error occurred: " + e.getMessage());
					}
					break;

				case 6:
					System.out.println("Exiting...");
					// Explicitly stop the Spring Boot application after user exits the menu
					SpringApplication.exit(context, () -> 0);  // Exit code 0 indicates a successful shutdown

					break;

				default:
					System.out.println("Invalid option. Please try again.");
			}
		} while (choice != 6);
		// After exiting the loop, print a confirmation message
		System.out.println("Application terminated.");
	}

	// Helper method to create headers with Basic Authentication
	private HttpHeaders createHeadersWithBasicAuth() {
		HttpHeaders headers = new HttpHeaders();
		String auth = USERNAME + ":" + PASSWORD;
		String encodedAuth = new String(Base64.encodeBase64String(auth.getBytes()));
		headers.set("Authorization", "Basic " + encodedAuth);
		return headers;
	}
}
