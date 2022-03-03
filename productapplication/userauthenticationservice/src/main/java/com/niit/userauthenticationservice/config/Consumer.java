package com.niit.userauthenticationservice.config;
import com.niit.userauthenticationservice.exception.CustomerAlreadyExist;
import com.niit.userauthenticationservice.model.Customer;
import com.niit.userauthenticationservice.rabbitmq.CustomerDTO;
import com.niit.userauthenticationservice.service.CustomerServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    //calling the method from userAuthentication serviceImpl for we will use the DTO class for Service
    @Autowired
    private CustomerServiceImpl customerService;

    //queue name what is defined in configuration of productService
    @RabbitListener(queues ="customer_queue")
    public void getCustomerDtoFromRabbitMq(CustomerDTO customerDTO)throws CustomerAlreadyExist {

        Customer customer =new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setPassword(customerDTO.getPassword());
        customerService.saveCustomer(customer);

    }//end of the function
}//end of class
