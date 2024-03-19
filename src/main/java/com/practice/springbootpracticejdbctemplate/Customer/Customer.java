package com.practice.springbootpracticejdbctemplate.Customer;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(
        name = "customertable",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customertable_unique_email_address",
                        columnNames = "email"
                )
        }
)
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customertable_id_seq",
            sequenceName = "customertable_id_seq",
            allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "customertable_id_seq")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String gender;

    public Customer(Integer id, String name, String email, Integer age, String country, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.gender = gender;
    }
    public Customer(){}

    public Customer(String name, String email,Integer age, String country, String gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Customer customer = (Customer) object;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age) && Objects.equals(country, customer.country) && Objects.equals(gender, customer.gender);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, email, age, country, gender);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
