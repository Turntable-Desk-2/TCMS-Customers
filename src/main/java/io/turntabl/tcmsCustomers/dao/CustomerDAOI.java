package io.turntabl.tcmsCustomers.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.tcmsCustomers.models.CustomerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class CustomerDAOI implements CustomerDAO {

    @Autowired
    JdbcTemplate template;

    @ApiOperation("Add a New Customer")
    @Override
    @PostMapping("/api/v1/add_new_customer")
    public void addNewCustomer() {
    }

    @ApiOperation("Get all Customers")
    @Override
    @GetMapping("/api/v1/get_all_customers")
    public List<CustomerTO> getAllCustomers() {
        return this.template.query(
                "select * from customers",
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Update a Customer Info")
    @Override
    @PutMapping("/api/v1/update_customer")
    public String updateCustomerInfo(Integer id) {
        return null;
    }

    @ApiOperation("Search For Customer by ID")
    @Override
    @GetMapping("/api/v1/search_customer_by_id")
    public List<CustomerTO> searchForCustomerByID(Integer id) {
        return this.template.query(
                "select * from customers where customer_id = ? ", new Object[]{id},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Search customer By Name")
    @Override
    @GetMapping("/api/v1/search_customer_by_name")
    public List<CustomerTO> searchForCustomerByName(String name) {
        return this.template.query(
                "select * from customers where customer_name like ? ", new Object[]{name+"%"},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }


    @ApiOperation("Get Customer By Level")
    @Override
    @GetMapping("/api/v1/customers/search_customer_by_level")
    public List<CustomerTO> searchForCustomerByLevel(String level) {
        return this.template.query(
                "select * from customers where customer_level = ? ", new Object[]{level},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }
}
