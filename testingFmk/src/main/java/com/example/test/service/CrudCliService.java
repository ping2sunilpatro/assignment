/*
package com.example.test.service;

import com.example.test.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.myapp.*;

import java.util.List;
import java.util.Optional;

@Service
public class CrudCliService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.customersToCustomerResponses(customers);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            customer.setId(id);  // Ensure we update the correct customer
            return customerRepository.save(customer);
        }
        throw new CustomerUpdateException("Failed to update customer with id " + id);
    }

    public void deleteCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer not found with id " + id);
        }
    }
}*/
