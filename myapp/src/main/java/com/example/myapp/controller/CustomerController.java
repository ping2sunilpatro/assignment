package com.example.myapp.controller;

import com.example.myapp.entity.Customer;
import com.example.myapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customersList = customerService.getAllCustomers();
        if (customersList.isEmpty()) {
            // If no customers are found, return an error response
            System.out.println("No customers found");
            return null;
        } else {
            // If customers are found, return the list of customers
            return new ResponseEntity<>(customersList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        // Fetch existing customer
        Customer existingCustomer = customerService.getCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        // Update fields if provided (you can update only selected fields if the user doesn't provide some)
        existingCustomer.setId(id);
        existingCustomer.setFirstName(customerDetails.getFirstName());
        existingCustomer.setMiddleName(customerDetails.getMiddleName());
        existingCustomer.setLastName(customerDetails.getLastName());
        existingCustomer.setEmailAddress(customerDetails.getEmailAddress());
        existingCustomer.setCountryCode(customerDetails.getCountryCode());
        existingCustomer.setAreaCode(customerDetails.getAreaCode());
        existingCustomer.setPrefix(customerDetails.getPrefix());
        existingCustomer.setLineNumber(customerDetails.getLineNumber());


        // Save the updated customer
        return customerService.saveCustomer(existingCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
