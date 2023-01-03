package com.stackroute.recruiterservice.controller;
import com.stackroute.recruiterservice.dto.RecruiterDto;
import com.stackroute.recruiterservice.dto.Status;
import com.stackroute.recruiterservice.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/getall")
    public ResponseEntity<List<RecruiterDto>> getAllRecruiters(){
        return new ResponseEntity<List<RecruiterDto>>(recruiterService.getAllRecruiters(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RecruiterDto> getRecruiterById(@PathVariable long id){
        return new ResponseEntity<RecruiterDto>(recruiterService.getRecruiterById(id),HttpStatus.OK);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Status> updateRecruiter(@RequestBody @Valid RecruiterDto recruiterDto,@PathVariable long id){
        return new ResponseEntity<Status>(recruiterService.updateRecruiter(recruiterDto,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Status> deleteRecruiterById(@PathVariable long id){
        return new ResponseEntity<Status>(recruiterService.deleteRecruiterById(id),HttpStatus.OK);
    }

}
