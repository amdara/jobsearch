package com.stackroute.jobseekerservice.rabbitmq;

import com.stackroute.jobseekerservice.util.Constants;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /*
    created queue for email,chat and feedback
    */

    @Bean
    public Queue emailQueue(){
        return new Queue(Constants.EMAIL_QUEUE);
    }

    @Bean
    public Queue jobsQueue() {
        return new Queue(Constants.JOB_QUEUE);
    }

    @Bean
    public Queue chatQueue(){
        return new Queue(Constants.CHAT_QUEUE);
    }

    @Bean
    public Queue feedbackQueue() {
        return new Queue(Constants.FEEDBACK_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constants.JOBSEEKER_EXCHANGE);
    }

    /*
      binding queue for email,job search,chat and feedback
   */
    @Bean
    public Binding emailBinding(Queue emailQueue,TopicExchange exchange){
        return BindingBuilder
                .bind(emailQueue)
                .to(exchange)
                .with(Constants.EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding jobBinding(Queue jobsQueue,TopicExchange exchange) {
        return BindingBuilder
                .bind(jobsQueue)
                .to(exchange)
                .with(Constants.ROUTING_KEY);
    }

    @Bean
    public Binding chatBinding(Queue chatQueue,TopicExchange exchange) {
        return BindingBuilder
                .bind(chatQueue)
                .to(exchange)
                .with(Constants.CHAT_ROUTING_KEY);
    }

    @Bean
    public Binding feedbackBinding(Queue feedbackQueue,TopicExchange exchange) {
        return BindingBuilder
                .bind(feedbackQueue)
                .to(exchange)
                .with(Constants.FEEDBACK_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
