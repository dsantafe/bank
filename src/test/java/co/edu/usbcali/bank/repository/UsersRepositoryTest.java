package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.UserType;
import co.edu.usbcali.bank.domain.Users;

@SpringBootTest
@Rollback(false)
class UsersRepositoryTest {
	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	String userEmail = "david.santafe@gmail.com";

	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(usersRepository, "The usersRepository is null");
		assertNotNull(userTypeRepository, "The userTypeRepository is null");
	}

	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		Optional<Users> userOptional = usersRepository.findById(userEmail);
		assertFalse(userOptional.isPresent(), "The user exist");

		Users user = new Users();
		user.setUserEmail(userEmail);
		user.setName("David Santafe");
		user.setEnable("S");

		Optional<UserType> userTypeOptional = userTypeRepository.findById(1L);
		assertTrue(userTypeOptional.isPresent(), "The user type doesn't exist");

		user.setUserType(userTypeOptional.get());

		usersRepository.save(user);
	}
	
	@Test
	@DisplayName("findById")
	void btest() {
		Optional<Users> userOptional = usersRepository.findById(userEmail);;
		assertTrue(userOptional.isPresent(), "The user doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<Users> userOptional = usersRepository.findById(userEmail);;
		assertTrue(userOptional.isPresent(), "The user doesn't exist");		
		
		userOptional.ifPresent(entity -> {
			entity.setName("David Santafe Zorrilla");
			usersRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<Users> userOptional = usersRepository.findById(userEmail);;
		assertTrue(userOptional.isPresent(), "The user doesn't exist");	

		userOptional.ifPresent(client -> {
			usersRepository.delete(client);
		});
	}
}
