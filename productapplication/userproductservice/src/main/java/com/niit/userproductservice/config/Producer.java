package com.niit.userproductservice.config;

import com.niit.userproductservice.rabbitmq.CustomerDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer{

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;


    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange){
        this.exchange=exchange;
        this.rabbitTemplate=rabbitTemplate;
    }//end of function


    public void sendMessageToRabbitMq(CustomerDTO customerDTO){
        //convert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"customer_routing",customerDTO);
    }//end of function

}//end of the class
