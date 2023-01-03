package com.stackroute.recruiterservice.consumer;
import com.stackroute.recruiterservice.constant.Constant;
import com.stackroute.recruiterservice.dto.SignupDto;
import com.stackroute.recruiterservice.modal.Recruiter;
import com.stackroute.recruiterservice.repository.RecruiterRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMessageListener {

        @Autowired
        private RecruiterRepository recruiterRepository;

        @RabbitListener(queues = Constant.CONSUMER_AUTHENTICATION_RECRUITER_QUEUE)
        public void authenticationMessageListener(SignupDto signupDto) {
            Recruiter recruiter=new Recruiter();
            recruiter.setId(signupDto.getId());
            recruiter.setEmail(signupDto.getEmail());
            recruiterRepository.save(recruiter);
            System.out.println("recruiter service"+recruiter);
        }
}
