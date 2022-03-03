package com.niit.userproductservice.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class MessageConfiguration{

    private String exchangeName="customer_exchange";
    private String registerQueue="customer_queue";

    //bean for an Exchange
    @Bean //import from amqp.core
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue registerQueue(){
        //return new Queue(registerQueue,false);//just to figure out
        return new Queue(registerQueue);//it takes two parameter string and boolean durable
    }//end of function

    //to convert the object data to binary format so that RabbitMQ will accept it, so we are using the library Jackson2JsonMessageConvertor
    @Bean
    Jackson2JsonMessageConverter producerJacksonConvertor(){return new Jackson2JsonMessageConverter();}

    //bean for rabbit template   ////import from connection factory from amqp.core
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //the message that comes to RabbitTemplate is to be converted
        rabbitTemplate.setMessageConverter(producerJacksonConvertor());
        return rabbitTemplate;
    }//end of function


    //create a Binding Bean
    @Bean
    public Binding bindingUser(Queue registerQueue, DirectExchange exchange){
        //binding builder from amqp.core
        //with() is for defining routing key whereas the user_routing is the routing key and during consuming we need to use this routing key
        //binding Queue with exchange
        //return BindingBuilder.bind(registerQueue()).to(exchange).with("customer_routing"); //this will also work the same way
        return BindingBuilder.bind(registerQueue()).to(exchange).with("customer_routing");
    }
}//end of class