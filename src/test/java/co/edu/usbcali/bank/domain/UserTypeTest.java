package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest	
@Rollback(false)
class UserTypeTest {

	@PersistenceContext
	EntityManager entityManager;
	
	Long ustyId = 4L;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {		
		assertNotNull(entityManager);
		UserType userType = entityManager.find(UserType.class, ustyId);		
		assertNull(userType,"The user type exist");
		
		userType = new UserType();
		userType.setUstyId(0L);
		userType.setName("PROMOTOR");
		userType.setEnable("S");
		
		entityManager.merge(userType);		
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		UserType userType = entityManager.find(UserType.class, ustyId);					
		assertNotNull(userType, "The user type doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		UserType userType = entityManager.find(UserType.class, ustyId);					
		assertNotNull(userType, "The user type doesn't exist");		
		
		userType.setEnable("N");
		
		entityManager.merge(userType);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		UserType userType = entityManager.find(UserType.class, ustyId);					
		assertNotNull(userType, "The user type doesn't exist");					
				
		entityManager.remove(userType);
	}

}
