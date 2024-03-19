package com.practice.springbootpracticejdbctemplate.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();
    boolean existsWithEmail(String email);
    void addCustomerToDB(Customer customer);
}
