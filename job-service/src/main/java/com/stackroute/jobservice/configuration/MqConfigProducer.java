package com.stackroute.jobservice.configuration;

import com.stackroute.jobservice.util.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfigProducer {

   @Bean
   public DirectExchange directExchange(){

       return new DirectExchange(Constants.EXCHANGE_1);
   }
   @Bean
   public Jackson2JsonMessageConverter producerMessageConverter(){

       return new Jackson2JsonMessageConverter();
   }

   @Bean
   public Queue registerQueue(){

       return new Queue(Constants.QUEUE_1);
   }

   @Bean
   public Binding bindingJob(Queue registerQueue, DirectExchange directExchange){
       return BindingBuilder.bind(registerQueue()).to(directExchange).with(Constants.ROUTING_KEY_1);
   }
    //create Rabbit MQ template .connection factory as input parameter and we are going to create rabbit mq template
    public RabbitTemplate template(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter());
        return rabbitTemplate;
    }
}
