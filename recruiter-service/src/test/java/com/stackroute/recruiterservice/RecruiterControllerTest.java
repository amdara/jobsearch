package com.stackroute.recruiterservice;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.*;
import com.stackroute.recruiterservice.constant.Constant;
import com.stackroute.recruiterservice.controller.RecruiterController;
import com.stackroute.recruiterservice.dto.RecruiterDto;
import com.stackroute.recruiterservice.dto.Status;
import com.stackroute.recruiterservice.service.RecruiterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RecruiterController.class)
@ImportResource({"classpath*:application-context.xml"})
class RecruiterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RecruiterService recruiterService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<RecruiterDto> recruiterDtoList ;

    @BeforeEach
    void contextLoads() {

        this.recruiterDtoList =  new ArrayList<>();
        RecruiterDto recruiterDto=new RecruiterDto(101,"Amazon","MNC","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153698");
        RecruiterDto recruiterDto2=new RecruiterDto(102,"CTS","MNC","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153698");
        this.recruiterDtoList.add(recruiterDto);
        this.recruiterDtoList.add(recruiterDto2);

    }

    @Test
    void testGetAllRecruiters() throws Exception{
        when(recruiterService.getAllRecruiters()).thenReturn(recruiterDtoList);

        this.mockMvc.perform(get("/recruiter/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(recruiterDtoList.size())));
    }

    @Test
    void testGetRecruiterById() throws Exception{
        RecruiterDto recruiterDto=new RecruiterDto(101,"Amazon","MNC","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153698");
        long id = 101;
        when(recruiterService.getRecruiterById(id)).thenReturn(recruiterDto);
        this.mockMvc.perform(get("/recruiter/get/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is(recruiterDto.getCompanyName())))
                .andExpect(jsonPath("$.firstName", is(recruiterDto.getFirstName())));
    }



    @Test
    void testUpdateRecruiterById() throws Exception{
        RecruiterDto recruiterDto=new RecruiterDto(101,"Amazon","MNC","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153698");
        long id=101;
        when(recruiterService.updateRecruiter(recruiterDto, id)).thenReturn(new Status(Constant.SUCCESS_UPDATE_VALUE,HttpStatus.CREATED));
        Status status=recruiterService.updateRecruiter(recruiterDto,id);
        mockMvc.perform(put("/recruiter/put/{id}",id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recruiterDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(status.getMessage())));
    }

    @Test
    void testDeleteRecruiterById() throws Exception
    {
        long id = 101;
        when(recruiterService.deleteRecruiterById(id)).thenReturn(new Status(Constant.SUCCESS_DELETE_VALUE,HttpStatus.OK));
        this.mockMvc.perform(get("/recruiter/delete/{id}",id)).andReturn();
    }

}