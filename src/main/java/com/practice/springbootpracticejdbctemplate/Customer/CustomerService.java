package com.practice.springbootpracticejdbctemplate.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    // Function to retrieve all Customers from Database
    public List<Customer> getAllCustomers(){
        return customerDAO.getCustomers();
    }
}
