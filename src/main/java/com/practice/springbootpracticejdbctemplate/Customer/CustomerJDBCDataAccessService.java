package com.practice.springbootpracticejdbctemplate.Customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController("JDBC")
public class CustomerJDBCDataAccessService implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getCustomers() {
        var sql = """
                SELECT id,name,age,email,country,gender FROM customertable
                """;
        RowMapper<Customer> rowMapper = (rs,rowNum) ->{
            Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    rs.getString("country"),
                    rs.getString("gender")
            );
            return customer;
        };

        return jdbcTemplate.query(sql,rowMapper);
    }


    // Function to check if email is present in Database
    @Override
    public boolean existsWithEmail(String email) {
        var sql = """
                SELECT count(id) FROM customertable WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,email);
        return count != null && count > 0;
    }


    // Function to add new customer into database
    @Override
    public void addCustomerToDB(Customer customer) {
        var sql = """
                INSERT INTO customertable (name,age,email,country,gender) VALUES (?,?,?,?,?)
                """;
        jdbcTemplate.update(sql,customer.getName(),customer.getAge(),customer.getEmail(),customer.getCountry(),customer.getGender());
    }

    @Override
    public Optional<Customer> getCustomerByID(Integer id) {
        var sql = """
                 SELECT id,name,age,email,country,gender FROM customertable WHERE id = ?
                """;
        RowMapper<Customer> customerRowMapper= (rs, rowNum) -> {
            Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    rs.getString("country"),
                    rs.getString("gender")
            );
            return customer;
        };
        return jdbcTemplate.query(sql,customerRowMapper,id).stream().findFirst();
    }
}
