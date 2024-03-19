package com.practice.springbootpracticejdbctemplate.Customer;

import com.practice.springbootpracticejdbctemplate.Exception.DuplicateConflictException;
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

    // Function to add user if email is not taken if taken throw error
    public void addCustomer(CustomerRegisterRequest customerRegisterRequest){
        if(customerDAO.existsWithEmail(customerRegisterRequest.email())){
            throw new DuplicateConflictException("Email has already taken!...");
        }
        Customer customer = new Customer(
                customerRegisterRequest.name(),
                customerRegisterRequest.email(),
                customerRegisterRequest.age(),
                customerRegisterRequest.country(),
                customerRegisterRequest.gender()
        );
        customerDAO.addCustomerToDB(customer);
    }
}
