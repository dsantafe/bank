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
class UsersTest {

	@PersistenceContext
	EntityManager entityManager;

	String userEmail = "david.santafe@gmail.com";

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		assertNotNull(entityManager);
		Users users = entityManager.find(Users.class, userEmail);
		assertNull(users, "The user exist");

		users = new Users();
		users.setUserEmail(userEmail);
		users.setName("David Santafe");
		users.setEnable("S");

		UserType userType = entityManager.find(UserType.class, 1L);
		assertNotNull(userType, "The user type doesn't exist");

		users.setUserType(userType);

		entityManager.persist(users);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		Users users = entityManager.find(Users.class, userEmail);
		assertNotNull(users, "The user doesn't exist");
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		Users users = entityManager.find(Users.class, userEmail);
		assertNotNull(users, "The user doesn't exist");

		users.setName("David Santafe Zorrilla");

		entityManager.merge(users);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		Users users = entityManager.find(Users.class, userEmail);
		assertNotNull(users, "The user doesn't exist");

		entityManager.remove(users);
	}
}
