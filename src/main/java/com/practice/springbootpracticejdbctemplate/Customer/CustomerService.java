package com.practice.springbootpracticejdbctemplate.Customer;

import com.practice.springbootpracticejdbctemplate.Exception.DuplicateConflictException;
import com.practice.springbootpracticejdbctemplate.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Function to get customer with given id if not found throw error
    public Customer getCustomerByID(Integer id){
        return customerDAO.getCustomerByID(id).orElseThrow(()-> new ResourceNotFoundException("Customer with given id: %s not found in the database sorry!..".formatted(id)));
    }

    // Function to find and delete Customer from database with given id
    public void deleteCustomer(Integer id){
        if(customerDAO.existsWithId(id)){
            customerDAO.deleteCustomerFromDB(id);
        }
        else {
            throw new ResourceNotFoundException("Customer with ID %s has been already deleted from database!".formatted(id));
        }
    }
}
