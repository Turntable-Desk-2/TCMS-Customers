package io.turntabl.tcmsCustomers.extra;

public class DBVars {
    public static String ADD_NEW_CUSTOMER = "insert into customers " +
            "(customer_id, customer_name, customer_email, customer_address, " +
            "customer_telephone, customer_level) values (?, ?, ?, ?, ?, ?)";
    public static String GET_CUSTOMERS = "select * from customers";
    public static String GET_CUSTOMER_BY_ID = "select * from customers where customer_id = ? ";
    public static String GET_CUSTOMER_BY_NAME = "select * from customers where customer_name like ? ";
    public static String GET_CUSTOMER_BY_LEVEL = "select * from customers where customer_level = ? ";
    public static String DELETE_CUSTOMER = "delete from customers where customer_id = ? ";
    public static String UPDATE_CUSTOMER = "update customers set customer_name = ?, customer_email = ?, customer_address = ?, customer_telephone = ?, customer_level=? " +
            " where customer_id = ? ";
}
