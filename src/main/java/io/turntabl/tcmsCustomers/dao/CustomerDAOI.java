package io.turntabl.tcmsCustomers.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.tcmsCustomers.models.CustomerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class CustomerDAOI implements CustomerDAO {

    @Autowired
    JdbcTemplate template;

    @Override
    public void addNewCustomer() {
    }

    @ApiOperation("Get all Customers")
    @Override
    @GetMapping("/api/v1/customers")
    public List<CustomerTO> getAllCustomers() {
        return this.template.query(
                "select * from customers",
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Update a Customer Info")
    @Override
    @PutMapping("/api/v1/customers/update")
    public void updateCustomerInfo(Integer id) {

    }

    @ApiOperation("Search For Customer by ID")
    @Override
    @GetMapping("/api/v1/customers/id")
    public CustomerTO searchForCustomerByID(Integer id) {
        return null;
    }

    @ApiOperation("Search customer By Name")
    @Override
    @GetMapping("/api/v1/customers/id")
    public List<CustomerTO> searchForCustomerByName(String name) {
        return null;
    }


    @ApiOperation("Get Customer By Level")
    @Override
    @GetMapping("/api/v1/customers/id/level")
    public List<CustomerTO> searchForCustomerByLevel(String level) {
        return null;
    }
}
