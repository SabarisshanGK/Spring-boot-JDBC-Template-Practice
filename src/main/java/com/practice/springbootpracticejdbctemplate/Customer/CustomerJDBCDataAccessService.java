package com.practice.springbootpracticejdbctemplate.Customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("JDBC")
public class CustomerJDBCDataAccessService implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getCustomers() {
        var sql = """
                SELECT id,name,age,email,country FROM customertable
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
}
