package io.turntabl.tcmsCustomers.dao;

import io.turntabl.tcmsCustomers.models.CustomerTO;

import java.util.List;

public interface CustomerDAO {
    public void addNewCustomer();
    public List<CustomerTO> getAllCustomers();
    public String updateCustomerInfo(Integer id);
    public List<CustomerTO> searchForCustomerByID(Integer id);
    public List<CustomerTO> searchForCustomerByName(String name);
    public List<CustomerTO> searchForCustomerByLevel(String level);
}
