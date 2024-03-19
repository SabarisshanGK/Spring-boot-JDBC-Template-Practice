package com.practice.springbootpracticejdbctemplate.Customer;

public record CustomerRegisterRequest(String name, String email, Integer age, String country, String gender) {}
