package io.turntabl.tcmsCustomers.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.tcmsCustomers.extra.DBVars;
import io.turntabl.tcmsCustomers.pubsub.Publisher;
import io.turntabl.tcmsCustomers.models.CustomerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class CustomerDAOI implements CustomerDAO {

    @Autowired
    JdbcTemplate template;

    @ApiOperation("Add a New Customer")
    @Override
    @PostMapping("/api/v1/customers")
    public void addNewCustomer(@RequestBody CustomerTO customer) {
        template.update(DBVars.ADD_NEW_CUSTOMER, customer.getCustomer_id(), customer.getCustomer_name(), customer.getCustomer_email(), customer.getCustomer_address(), customer.getCustomer_telephone(), customer.getCustomer_level());
        Publisher.publis("new_customer", "New Customer: " + customer.getCustomer_name() + " added");
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("Get all Customers")
    @Override
    @GetMapping("/api/v1/customers")
    public List<CustomerTO> getAllCustomers() {
        Publisher.publis("customers", "Getting all Customers");
        return this.template.query(
                DBVars.GET_CUSTOMERS,
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Update a Customer Info")
    @Override
    @PutMapping("/api/v1/customers/{id}")
    public void updateCustomerInfo(@PathVariable("id") Integer id, CustomerTO customer) {
        int status = template.update(DBVars.UPDATE_CUSTOMER,
                customer.getCustomer_id(), customer.getCustomer_name(), customer.getCustomer_email(), customer.getCustomer_address(),
                customer.getCustomer_telephone(), customer.getCustomer_level());
        Publisher.publis("updater", "Customer: " + customer.getCustomer_id() + " Updated");
    }

    @CrossOrigin
    @ApiOperation("Get For Customer by ID")
    @Override
    @GetMapping("/api/v1/customers/{id}")
    public List<CustomerTO> searchForCustomerByID(@PathVariable("id") Integer id) {
        Publisher.publis("customer", "Getting a Customer with id " + id);
        return this.template.query(
                DBVars.GET_CUSTOMER_BY_ID, new Object[]{id},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @CrossOrigin
    @ApiOperation("Search Customer By Name")
    @Override
    @GetMapping("/api/v1/customers/search")
    public List<CustomerTO> searchForCustomerByName(@RequestParam(value = "name") String name) {
        Publisher.publis("customers", "Getting Customers by name " + name);
        return this.template.query(
                DBVars.GET_CUSTOMER_BY_NAME, new Object[]{name+"%"},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @CrossOrigin
    @ApiOperation("Search Customer By Level")
    @Override
    @GetMapping("/api/v1/customers/searchLevel")
    public List<CustomerTO> searchForCustomerByLevel(@RequestParam(value = "level") String level) {
        Publisher.publis("customers", "Getting Customers with level " + level);
        return this.template.query(
                DBVars.GET_CUSTOMER_BY_LEVEL, new Object[]{level},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Delete a Customer")
    @Override
    @DeleteMapping("/api/v1/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        this.template.update(DBVars.DELETE_CUSTOMER, id);
        Publisher.publis("remove", "Removed a Customer with id " + id);
    }
}
