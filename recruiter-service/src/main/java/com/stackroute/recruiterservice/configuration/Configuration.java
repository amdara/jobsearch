package com.stackroute.recruiterservice.configuration;
import com.stackroute.recruiterservice.constant.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import javax.validation.Validator;
import java.util.Collections;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(Validator validator){
        return new ValidatingMongoEventListener(validator);
    }
    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("Recruiters API","Recruiter management api","V1.0","http://localhost:8099/fitmyjob"
                        ,new Contact("name","url","shanthi.sannasi@globallogic.com"),"opensource","", Collections.emptyList()));
    }
    //Create queue to produce data
    @Bean
    public Queue jobQueue() {
        return new Queue(Constant.PRODUCER_RECRUITER_TO_JOB_QUEUE);
    }
    @Bean
    public Queue chatQueue() {return new Queue(Constant.PRODUCER_RECRUITER_CHAT_QUEUE);}
    @Bean
    public Queue feedbackQueue() {return new Queue(Constant.PRODUCER_RECRUITER_FEEDBACK_QUEUE);}
    //Topic exchange
    @Bean
    public TopicExchange topicExchange() {return new TopicExchange(Constant.EXCHANGE);}

    //Bind together (queue and exchange).
    @Bean
    public Binding jobBinding(Queue jobQueue, TopicExchange jobExchange) {
        return BindingBuilder
                .bind(jobQueue)
                .to(jobExchange)
                .with(Constant.PRODUCER_RECRUITER_TO_JOB_ROUNTING_KEY);
    }
    //Bind together (queue and exchange).
    @Bean
    public Binding chatBinding(Queue chatQueue, TopicExchange chatExchange) {
        return BindingBuilder
                .bind(chatQueue)
                .to(chatExchange)
                .with(Constant.PRODUCER_RECRUITER_CHAT_ROUNTING_KEY);
    }
    //Bind together (queue and exchange).
    @Bean
    public Binding feedbackBinding(Queue feedbackQueue, TopicExchange feedbackExchange) {
        return BindingBuilder
                .bind(feedbackQueue)
                .to(feedbackExchange)
                .with(Constant.PRODUCER_RECRUITER_FEEDBACK_ROUNTING_KEY);
    }
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
