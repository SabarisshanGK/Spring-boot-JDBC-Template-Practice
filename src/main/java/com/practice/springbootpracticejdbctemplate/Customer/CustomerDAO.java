package com.practice.springbootpracticejdbctemplate.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> getCustomers();
    boolean existsWithEmail(String email);
    void addCustomerToDB(Customer customer);
    Optional<Customer> getCustomerByID(Integer id);
}
