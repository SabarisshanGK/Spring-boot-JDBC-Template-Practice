package com.practice.springbootpracticejdbctemplate.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET ALL USERS FROM DATABASE WITH API ENDPOINT: http://localhost:8000/testapi/customers
    @GetMapping("/testapi/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }


    // POST USER TO THE DATABASE WITH API ENDPOINT: http://localhost:8000/testapi/add-customer
    @PostMapping("/testapi/add-customer")
    public void addCustomer(@RequestBody CustomerRegisterRequest customerRegisterRequest){
        customerService.addCustomer(customerRegisterRequest);
    }
}
