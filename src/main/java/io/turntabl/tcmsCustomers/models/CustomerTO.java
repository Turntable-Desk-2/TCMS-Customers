package io.turntabl.tcmsCustomers.models;

public class CustomerTO {
    private int customer_id;
    private String customer_name;
    private String customer_email;
    private String customer_address;
    private String customer_telephone;
    private String customer_level;

    public CustomerTO() {
    }

    public CustomerTO(int customer_id, String customer_name, String customer_email, String customer_address, String customer_telephone, String customer_level) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_address = customer_address;
        this.customer_telephone = customer_telephone;
        this.customer_level = customer_level;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_telephone() {
        return customer_telephone;
    }

    public void setCustomer_telephone(String customer_telephone) {
        this.customer_telephone = customer_telephone;
    }

    public String getCustomer_level() {
        return customer_level;
    }

    public void setCustomer_level(String customer_level) {
        this.customer_level = customer_level;
    }

    @Override
    public String toString() {
        return "CustomerTO{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", customer_telephone='" + customer_telephone + '\'' +
                ", customer_level='" + customer_level + '\'' +
                '}';
    }
}
