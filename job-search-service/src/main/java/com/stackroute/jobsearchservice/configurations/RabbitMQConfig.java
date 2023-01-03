package com.stackroute.jobsearchservice.configurations;

import com.stackroute.jobsearchservice.util.Constants;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class RabbitMQConfig {

    //creating queue to Job Seeker service to produce data
    @Bean
    public Queue jobSeekerQueue() {
        return new Queue(Constants.QUEUE);
    }

    //Creating exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constants.EXCHANGE);
    }

    //Binding Queue and Exchange
    @Bean
    public Binding jobSeekerBinding() {
        return BindingBuilder
                .bind(jobSeekerQueue())
                .to(exchange())
                .with(Constants.ROUTING_KEY);
    }

    //Converts Java object to a JSON message
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //Rabbit Template is used to convert and send a message using RabbitMQ
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template=new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;

    }
}
