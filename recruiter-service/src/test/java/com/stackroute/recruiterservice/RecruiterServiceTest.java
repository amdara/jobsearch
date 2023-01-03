package com.stackroute.recruiterservice;
import com.stackroute.recruiterservice.dto.RecruiterDto;
import com.stackroute.recruiterservice.exception.IdNotFoundException;
import com.stackroute.recruiterservice.modal.Recruiter;
import com.stackroute.recruiterservice.repository.RecruiterRepository;
import com.stackroute.recruiterservice.service.RecruiterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ImportResource({"classpath*:application-context.xml"})
class RecruiterServiceTest {

	@Autowired
	private RecruiterService recruiterService;

	@MockBean
	private RecruiterRepository recruiterRepository;


	@Test
	public void testGetRecruiterById() {
		long id=100;
		Recruiter recruiter=new Recruiter(100,"TCL","MNC","shanthi.sannasi@globallogic.com","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699");
		when(recruiterRepository.findById(id))
				.thenReturn(Optional.of(recruiter));
		String name1="Shanthi";
		String name2="Maha";
		RecruiterDto recruiterDto=recruiterService.getRecruiterById(100);
		//Positive testcase
		assertEquals(name1, recruiterDto.getFirstName());
		//Negative testcases
		assertNotEquals(name2,recruiterDto.getFirstName());
		//Exception testcase
		assertThrows(IdNotFoundException.class,()->{
			recruiterService.getRecruiterById(101);
		});
		//Null testcases
		assertNotNull(recruiterDto.getCompanyName());

	}

	@Test
	public void testGetAllRecruiters(){
		List<Recruiter> recruiterList=new ArrayList<>();
 		recruiterList.add(new Recruiter(102,"Apsi","MNC","shanthi.sannasi@globallogic.com","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699"));
		recruiterList.add(new Recruiter(103,"Globallogic","MNC","shanthi.sannasi@globallogic.com","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699"));
		Mockito.when(recruiterRepository.findAll())
				.thenReturn(recruiterList);
		//Positve testcases
		assertEquals(2,recruiterService.getAllRecruiters().size());
		//Negative testcases
		assertNotEquals(1,recruiterService.getAllRecruiters().size());
		//Null testcases
		assertNotNull(recruiterService.getAllRecruiters());
	}

	@Test
	public void testUpdateRecruiter(){

		long id=106;
		Recruiter recruiter=new Recruiter(106,"Amazon","MNC","shanthi.sannasi@globallogic.com","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699");
		Mockito.when(recruiterRepository.findById(id))
				.thenReturn(Optional.of(recruiter));
		Recruiter recruiter2=new Recruiter(106,"Tcl","MNC","shanthi.sannasi@globallogic.com","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699");
		RecruiterDto recruiterDto=new RecruiterDto(106,"verizon","MNC","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699");
		when(recruiterRepository.save(recruiter2)).thenReturn(recruiter2);
		assertThrows(IdNotFoundException.class,()->{recruiterService.getRecruiterById(101);});
		assertEquals(106,recruiterDto.getId());
	}

	@Test
	public void testDeleteRecruiterById(){
		Recruiter recruiter=new Recruiter(100,"TCL","MNC","shanthi.sannasi@globallogic.com","Perungudi","Chennai","Tamilnadu","Indai","RMZ millineni","Noida","Shanthi","Sannasi","HR","7200153699");
		when(recruiterRepository.findById(recruiter.getId()))
				.thenReturn(Optional.of(recruiter));
		recruiterService.deleteRecruiterById(recruiter.getId());
		verify(recruiterRepository,times(1)).deleteById(recruiter.getId());
	}

}
