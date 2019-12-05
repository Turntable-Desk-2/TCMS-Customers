package io.turntabl.tcmsCustomers.dao;

import io.turntabl.tcmsCustomers.models.CustomerTO;

import java.util.List;

public interface CustomerDAO {
    public void addNewCustomer(CustomerTO customer);
    public List<CustomerTO> getAllCustomers();
    public void updateCustomerInfo(Integer id, CustomerTO customer);
    public CustomerTO searchForCustomerByID(Integer id);
    public List<CustomerTO> searchForCustomerByName(String name);
    public List<CustomerTO> searchForCustomerByLevel(String level);
    public void deleteCustomer(Integer id);
}
