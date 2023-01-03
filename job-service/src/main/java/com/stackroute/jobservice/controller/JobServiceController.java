package com.stackroute.jobservice.controller;

import com.stackroute.jobservice.dao.JobDto;
import com.stackroute.jobservice.dao.Status;
import com.stackroute.jobservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/jobservice")
public class JobServiceController {

    @Autowired
    private JobService jobService;

    @PostMapping("/add")
    public ResponseEntity<JobDto> postJob(@Valid @RequestBody JobDto jobDto ){
        return new ResponseEntity<>(jobService.postJob(jobDto), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return new ResponseEntity<>(jobService.getAllPosts(), HttpStatus.OK);
    }

    @PutMapping("/update/{jobId}")
    public ResponseEntity<JobDto> updateJobById(@Valid @RequestBody JobDto jobDto, @PathVariable Long jobId) {
        return new ResponseEntity<>(jobService.updateJobById(jobId, jobDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<Status> deleteJobById(@PathVariable Long jobId) {
        return new ResponseEntity<Status>(jobService.deleteJobById(jobId), HttpStatus.OK);
    }
}
