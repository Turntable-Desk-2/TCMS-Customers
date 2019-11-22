package io.turntabl.tcmsCustomers.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.tcmsCustomers.extra.DBVars;
import io.turntabl.tcmsCustomers.models.CustomerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class CustomerDAOI implements CustomerDAO {

    @Autowired
    JdbcTemplate template;

    @Autowired
    RedisTemplate<String, CustomerTO> redisTemplate;

    @Autowired
    ChannelTopic topic;

    @ApiOperation("Add a New Customer")
    @Override
    @PostMapping("/api/v1/customers")
    public void addNewCustomer(CustomerTO customer) {
        template.update(DBVars.ADD_NEW_CUSTOMER, customer.getCustomer_id(), customer.getCustomer_name(), customer.getCustomer_email(), customer.getCustomer_address(), customer.getCustomer_telephone(), customer.getCustomer_level());
        redisTemplate.convertAndSend(topic.getTopic(), customer);
    }

    @ApiOperation("Get all Customers")
    @Override
    @GetMapping("/api/v1/customers")
    public List<CustomerTO> getAllCustomers() {
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
    }

    @ApiOperation("Get For Customer by ID")
    @Override
    @GetMapping("/api/v1/customers/{id}")
    public List<CustomerTO> searchForCustomerByID(@PathVariable("id") Integer id) {
        return this.template.query(
                DBVars.GET_CUSTOMER_BY_ID, new Object[]{id},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Search Customer By Name")
    @Override
    @GetMapping("/api/v1/customers/search")
    public List<CustomerTO> searchForCustomerByName(@RequestParam(value = "name") String name) {
        return this.template.query(
                DBVars.GET_CUSTOMER_BY_NAME, new Object[]{name+"%"},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Search Customer By Level")
    @Override
    @GetMapping("/api/v1/customers/searchLevel")
    public List<CustomerTO> searchForCustomerByLevel(@RequestParam(value = "level") String level) {
        return this.template.query(
                DBVars.GET_CUSTOMER_BY_LEVEL, new Object[]{level},
                new BeanPropertyRowMapper<CustomerTO>(CustomerTO.class));
    }

    @ApiOperation("Delete a Customer")
    @Override
    @DeleteMapping("/api/v1/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        this.template.update(DBVars.DELETE_CUSTOMER, id);
    }
}
