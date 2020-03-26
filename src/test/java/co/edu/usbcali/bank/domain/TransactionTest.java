package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(false)
class TransactionTest {

	@PersistenceContext
	EntityManager entityManager;

	Long tranId = 1024L;
	String accoId = "4640-0341-9387-5781";
	Long trtyId = 3L;
	String userEmail = "cfaier0@cafepress.com";

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		assertNotNull(entityManager);

		Transaction transaction = new Transaction();
		transaction.setTranId(0L);
		transaction.setAmount(160894372D);

		Date today = new Date();
		transaction.setDate(today);

		Account account = entityManager.find(Account.class, accoId);
		assertNotNull(account, "The account doesn't exist");
		transaction.setAccount(account);

		Users user = entityManager.find(Users.class, userEmail);
		assertNotNull(user, "The user doesn't exist");
		transaction.setUsers(user);

		TransactionType transactionType = entityManager.find(TransactionType.class, trtyId);
		assertNotNull(transactionType, "The transaction type doesn't exist");
		transaction.setTransactionType(transactionType);

		entityManager.merge(transaction);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		Transaction transaction = entityManager.find(Transaction.class, tranId);			
		assertNotNull(transaction,"The transaction doesn't exist");		
	}
}
