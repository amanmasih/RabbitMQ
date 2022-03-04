package com.niit.userauthenticationservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConfiguration {

    //Bean for conversion
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConvertor(){return new Jackson2JsonMessageConverter(); }
}//end of class
