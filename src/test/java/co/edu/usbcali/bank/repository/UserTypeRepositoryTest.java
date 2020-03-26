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

@SpringBootTest
@Rollback(false)
class UserTypeRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	UserTypeRepository userTypeRepository;

	Long ustyId = 4L;

	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
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
		
		UserType userType = new UserType();
		userType.setUstyId(0L);
		userType.setName("PROMOTOR");
		userType.setEnable("S");
		
		userTypeRepository.save(userType);
	}

	@Test
	@DisplayName("findById")
	void btest() {
		Optional<UserType> userTypeOptional = userTypeRepository.findById(ustyId);
		assertTrue(userTypeOptional.isPresent(), "The user type doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<UserType> userTypeOptional = userTypeRepository.findById(ustyId);
		assertTrue(userTypeOptional.isPresent(), "The user type doesn't exist");		
		
		userTypeOptional.ifPresent(entity -> {
			entity.setEnable("S");
			userTypeRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<UserType> userTypeOptional = userTypeRepository.findById(ustyId);
		assertTrue(userTypeOptional.isPresent(), "The user type doesn't exist");		

		userTypeOptional.ifPresent(entity -> {
			userTypeRepository.delete(entity);
		});
	}	

}
