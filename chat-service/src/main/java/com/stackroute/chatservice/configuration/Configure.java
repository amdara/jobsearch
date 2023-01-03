package com.stackroute.chatservice.configuration;

import com.stackroute.chatservice.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {

    @Bean
    public Queue chatQueue() {return new Queue(Constants.PRODUCER_RECRUITER_CHAT_QUEUE);}

    //Topic exchange
    @Bean
    public TopicExchange topicExchange() {return new TopicExchange(Constants.EXCHANGE);}

    //Bind together (queue and exchange).
    @Bean
    public Binding chatBinding(Queue chatQueue, TopicExchange chatExchange) {
        return BindingBuilder
                .bind(chatQueue)
                .to(chatExchange)
                .with(Constants.PRODUCER_RECRUITER_CHAT_ROUNTING_KEY);
    }
    //Bind together (queue and exchange).

    //Bind together (queue and exchange).

    //We are passing entire class so that we have to convert as json.so we use messageconverter to send particular queue
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //create Rabbit MQ template .connectionfactory as input paramter and we are going to create rabbit mq template
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template=new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
