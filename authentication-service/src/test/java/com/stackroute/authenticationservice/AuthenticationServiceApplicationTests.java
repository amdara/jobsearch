//package com.stackroute.authenticationservice;
//
//
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//class AuthenticationServiceApplicationTests {
///*
//	@InjectMocks
//	UserController userController;
//
//	@Mock
//	UserRepository userRepository;
//
//
//
//
//	@Test
//	public void signUpUser()
//
//	{
//
//
//		User user = new User();
//
//		SignUpDto signUpDto = new SignUpDto ("megala","megala@gmail.com","megala","ROLE_BUYER");
//
//		signUpDto.setUsername(user.getUsername());
//		signUpDto.setEmail(user.getEmail());
//		signUpDto.setPassword(user.getPassword());
//		signUpDto.setRole(user.getRole());
//		when(userRepository.save(user)).thenReturn(user);
//		assertEquals("megala@gmail.com", userRepository.findByUsernameOrEmail("megala", "megala@gmail.com"));
//
//	}
//
//	@Test
//	public void loginUser()
//
//	{
//		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password = passwordEncoder.encode("megala");
//
//		User user = new User();
//
//		LoginDto loginDto = new LoginDto("megala", password);
//		LoginDto saveLogin = userRepository.save();
//
//		assertThat(saveLogin).isNotNull();
//
//
//
//	}*/
//
//
//
//}
//
//
