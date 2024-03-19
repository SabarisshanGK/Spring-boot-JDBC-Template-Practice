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

    // Function to update a customer with given id
    public void updateCustomer(CustomerUpdateRequest customerUpdateRequest,Integer id){
        boolean checked = false;
        Customer customer = getCustomerByID(id);
        if(customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            checked = true;
        }
        if(customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())){
            String email = customerUpdateRequest.email();
            if(customerDAO.existsWithEmail(email)){
                throw new ResourceNotFoundException("Already exists");
            }
            customer.setEmail(email);
            checked = true;
        }
        if(customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(customer.getAge())){
            customer.setAge(customerUpdateRequest.age());
            checked = true;
        }
        if(customerUpdateRequest.country() != null && !customerUpdateRequest.country().equals(customer.getCountry())){
            customer.setCountry(customerUpdateRequest.country());
            checked = true;
        }
        if(customerUpdateRequest.gender() != null && !customerUpdateRequest.gender().equals(customer.getGender())){
            customer.setGender(customerUpdateRequest.gender());
            checked = true;
        }

        if(!checked){
            throw new DuplicateConflictException("No changes Found");
        }

        customerDAO.updateCustomerInDatabase(customer);
    }
}
