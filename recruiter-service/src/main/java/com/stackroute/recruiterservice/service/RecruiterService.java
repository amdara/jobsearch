package com.stackroute.recruiterservice.service;

import com.stackroute.recruiterservice.constant.Constant;
import com.stackroute.recruiterservice.dto.ChatAndFeedbackDto;
import com.stackroute.recruiterservice.dto.RecruiterDto;
import com.stackroute.recruiterservice.dto.Status;
import com.stackroute.recruiterservice.exception.EmptyValueException;
import com.stackroute.recruiterservice.exception.IdNotFoundException;
import com.stackroute.recruiterservice.modal.Recruiter;
import com.stackroute.recruiterservice.repository.RecruiterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RabbitTemplate template;


    /*
     * @Description : this method is used to get all recruiters
     *
     * @Param : It takes no parameter
     *
     * @returns : It returns List of recruitersDto object
     *
     * @Throws :
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */
    public List<RecruiterDto> getAllRecruiters() {
        Optional<List<Recruiter>> recruiterList=Optional.of(recruiterRepository.findAll());
        List<RecruiterDto> recruiterDtoList=new ArrayList<>();
        //it is not working
        recruiterList.orElseThrow(()->new EmptyValueException(Constant.EMPTY_DATA_RESPONSE));
         for(Recruiter recruiter:recruiterList.get()){
            recruiterDtoList.add(convertEntityToDto(recruiter));
        }
        return recruiterDtoList;
    }

    /*
     * @Description : this method is used to get recruiter by id
     *
     * @Param : It takes recruiter id as parameter
     *
     * @returns : It returns recruiterDto object
     *
     * @Throws : IdNotFoundException if recruiter id is not present
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */
     public RecruiterDto getRecruiterById(long id){
         Recruiter recruiter = recruiterRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Constant.NOT_FOUND_ID));
         RecruiterDto recruiterDto=convertEntityToDto(recruiter);
         return recruiterDto;
     }


    /*
     * @Description : this method is used to update recruiter object
     *
     * @Param : It takes RecruiterDto object and recruiter id as parameter
     *
     * @returns : It returns recruiterDto object
     *
     * @Throws : IdNotFoundException if recruiter id is not present
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */

    public Status updateRecruiter(RecruiterDto recruiterDto,long id){
        Recruiter recruiter = recruiterRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Constant.NOT_FOUND_ID));
        Recruiter recruiter1=recruiterRepository.save(updateDtoToEntity(recruiterDto,recruiter));
        //Produce recruiter object to job service
        template.convertSendAndReceive(Constant.EXCHANGE, Constant.PRODUCER_RECRUITER_TO_JOB_ROUNTING_KEY, recruiter1);
        //setting id and email into DTO class
        ChatAndFeedbackDto chatAndFeedbackDto=new ChatAndFeedbackDto();
        chatAndFeedbackDto.setId(recruiter1.getId());
        chatAndFeedbackDto.setEmail(recruiter1.getEmail());
        //Produce DTO class to chat service
        template.convertSendAndReceive(Constant.EXCHANGE,Constant.PRODUCER_RECRUITER_CHAT_ROUNTING_KEY,chatAndFeedbackDto);
        //Produce DTO class to feedback service
        template.convertSendAndReceive(Constant.EXCHANGE,Constant.PRODUCER_RECRUITER_FEEDBACK_ROUNTING_KEY,chatAndFeedbackDto);
        return new Status(Constant.SUCCESS_UPDATE_VALUE, HttpStatus.CREATED);
    }

    /*
     * @Description : this method is used to delete the recruiter data by passing id
     *
     * @Param : It takes Recruiter id as parameter
     *
     * @returns : It returns status object
     *
     * @Throws : IdNotFoundException if recruiter id is not present
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */
    public Status deleteRecruiterById(long id){
        recruiterRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Constant.NOT_FOUND_ID));
        recruiterRepository.deleteById(id);
        return new Status(Constant.SUCCESS_DELETE_VALUE,HttpStatus.OK);
    }

    /*
     * @Description : this method is used to convert Entity class to DTO class
     *
     * @Param : It takes RecruiterD object as parameter
     *
     * @returns : It returns recruiterDto object
     *
     * @Throws : Null
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */
    public RecruiterDto convertEntityToDto(Recruiter recruiter){
        RecruiterDto recruiterDto=modelMapper.map(recruiter,RecruiterDto.class);
        return recruiterDto;
    }


    /*
     * @Description : this method is used to convert Dto class to Entity class
     *
     * @Param : It takes RecruiterDto object as parameter
     *
     * @returns : It returns recruiter object
     *
     * @Throws : Null
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */
    public Recruiter convertDtoToEntity(RecruiterDto recruiterDto){
        Recruiter recruiter=modelMapper.map(recruiterDto,Recruiter.class);
        return recruiter;
    }

    /*
     * @Description : this method is used to convert Dto class to Entity class
     *
     * @Param : It takes RecruiterDto and Recruiter object as parameter
     *
     * @returns : It returns recruiter object
     *
     * @Throws : Null
     *
     * @Created by : Shanthi sannasi
     *
     * @Created Date : 21 Nov 2022
     *
     */
    public Recruiter updateDtoToEntity(RecruiterDto recruiterDto,Recruiter recruiter){
        modelMapper.map(recruiterDto,recruiter);
        return recruiter;
    }
}
