//package com.stackroute.feedbackservice.rabbitmq;
//
//import com.stackroute.feedbackservice.model.UserModel;
//import com.stackroute.feedbackservice.service.FeedbackService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import static com.stackroute.emailservice.rabbitmq.RabbitMQConfig.FEEDBACK_QUEUE;
//
//@Service
//public class FeedbackConsumer {
//
//    Log log = LogFactory.getLog(FeedbackConsumer.class);
//
//    @Autowired
//    FeedbackService feedbackService;
//
//    @RabbitListener(queues = FEEDBACK_QUEUE)
//    public void listener(UserModel userModel) {
//        log.info("Inside consumer");
//        feedbackService.addUser(userModel);
//    }
//}
