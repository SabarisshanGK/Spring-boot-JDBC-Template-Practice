package com.practice.springbootpracticejdbctemplate.Customer;

public record CustomerUpdateRequest(String name, String email, Integer age, String country, String gender) {}