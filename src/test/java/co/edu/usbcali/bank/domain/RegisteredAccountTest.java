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
class RegisteredAccountTest {

	@PersistenceContext
	EntityManager entityManager;
	
	String accoId = "0000-0000-0000-0000";
	Long clieId = 4040L;
	Long reacId = 9L;

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {		
		assertNotNull(entityManager);
		
		RegisteredAccount registeredAccount = new RegisteredAccount();
		registeredAccount.setReacId(0L);
		registeredAccount.setEnable("S");
		
		Client client = entityManager.find(Client.class, clieId);		
		assertNotNull(client,"The client doesn't exist");
				
		registeredAccount.setClient(client);
		
		Account account = entityManager.find(Account.class, accoId);		
		assertNotNull(account,"The account doesn't exist");	
		
		registeredAccount.setAccount(account);
		
		entityManager.merge(registeredAccount);		
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		RegisteredAccount registeredAccount = entityManager.find(RegisteredAccount.class, reacId);		
		assertNotNull(registeredAccount,"The registered account doesn't exist");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		RegisteredAccount registeredAccount = entityManager.find(RegisteredAccount.class, reacId);		
		assertNotNull(registeredAccount,"The registered account doesn't exist");
		
		registeredAccount.setEnable("N");
		
		entityManager.merge(registeredAccount);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		RegisteredAccount registeredAccount = entityManager.find(RegisteredAccount.class, reacId);			
		assertNotNull(registeredAccount,"The registered account doesn't exist");		
				
		entityManager.remove(registeredAccount);
	}
}
