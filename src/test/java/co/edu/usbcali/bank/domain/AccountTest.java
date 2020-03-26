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
class AccountTest {

	@PersistenceContext
	EntityManager entityManager;
	
	String accoId = "0000-0000-0000-0000";
	Long clieId = 4040L;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		
		assertNotNull(entityManager);
		Account account = entityManager.find(Account.class, accoId);		
		assertNull(account,"The account exist");
		
		account = new Account();
		account.setAccoId(accoId);
		account.setBalance(2032719D);
		account.setPassword("JIxErs");
		account.setEnable("S");
		account.setVersion(1L);	
		
		Client client = entityManager.find(Client.class, clieId);		
		assertNotNull(client,"The client doesn't exist");
				
		account.setClient(client);
		
		entityManager.persist(account);		
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		Account account = entityManager.find(Account.class, accoId);			
		assertNotNull(account,"The account doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		Account account = entityManager.find(Account.class, accoId);		
		assertNotNull(account,"The account doesn't exist");		
		
		account.setBalance(511124D);
		
		entityManager.merge(account);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		Account account = entityManager.find(Account.class, accoId);			
		assertNotNull(account,"The account doesn't exist");			
				
		entityManager.remove(account);
	}

}
