package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
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

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Transaction;
import co.edu.usbcali.bank.domain.TransactionType;
import co.edu.usbcali.bank.domain.Users;

@SpringBootTest
@Rollback(false)
class TransactionRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	TransactionTypeRepository transactionTypeRepository;

	Long tranId = 1024L;
	String accoId = "4640-0341-9387-5781";
	Long trtyId = 3L;
	String userEmail = "cfaier0@cafepress.com";
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(transactionRepository, "The transactionRepository is null");
		assertNotNull(accountRepository, "The accountRepository is null");
		assertNotNull(usersRepository, "The usersRepository is null");
		assertNotNull(transactionTypeRepository, "The transactionTypeRepository is null");
	}

	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		
		Transaction transaction = new Transaction();
		transaction.setTranId(0L);
		transaction.setAmount(160894372D);

		Date today = new Date();
		transaction.setDate(today);

		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(), "The account doesn't exist");
		transaction.setAccount(accountOptional.get());

		Optional<Users> userOptional = usersRepository.findById(userEmail);;
		assertTrue(userOptional.isPresent(), "The user doesn't exist");	
		transaction.setUsers(userOptional.get());

		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(), "The transaction type doesn't exist");
		transaction.setTransactionType(transactionTypeOptional.get());
		
		transactionRepository.save(transaction);
	}
	
	@Test
	@DisplayName("findById")
	void btest() {
		Optional<Transaction> transactionOptional = transactionRepository.findById(tranId);
		assertTrue(transactionOptional.isPresent(), "The transaction doesn't exist");		
	}
}
