package com.niit.userauthenticationservice.rabbitmq;

public class CustomerDTO {
    private String customerId;
    private String password;

    public CustomerDTO() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
