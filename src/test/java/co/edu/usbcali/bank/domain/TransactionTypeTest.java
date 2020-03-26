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
class TransactionTypeTest {

	@PersistenceContext
	EntityManager entityManager;
	
	Long trtyId = 4L;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {		
		assertNotNull(entityManager);
				
		TransactionType transactionType = new TransactionType();
		transactionType.setTrtyId(0L);
		transactionType.setName("ABONO");
		transactionType.setEnable("S");
		
		entityManager.merge(transactionType);		
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		TransactionType transactionType = entityManager.find(TransactionType.class, trtyId);			
		assertNotNull(transactionType,"The transactionType doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		TransactionType transactionType = entityManager.find(TransactionType.class, trtyId);			
		assertNotNull(transactionType,"The transactionType doesn't exist");	
		
		transactionType.setEnable("N");
		
		entityManager.merge(transactionType);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		TransactionType transactionType = entityManager.find(TransactionType.class, trtyId);			
		assertNotNull(transactionType,"The transactionType doesn't exist");			
				
		entityManager.remove(transactionType);
	}
}
