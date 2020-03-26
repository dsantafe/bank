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

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Client;

@SpringBootTest
@Rollback(false)
class AccountRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ClientRepository clientRepository;

	String accoId = "0000-0000-0000-0000";
	Long clieId = 4040L;
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(accountRepository, "The accountRepository is null");
		assertNotNull(clientRepository, "The clientRepository is null");
	}

	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertFalse(accountOptional.isPresent(), "The account exist");
		
		Account account = new Account();
		account = new Account();
		account.setAccoId(accoId);
		account.setBalance(2032719D);
		account.setPassword("JIxErs");
		account.setEnable("S");
		account.setVersion(1L);	
		
		Optional<Client> clientOptional = clientRepository.findById(clieId);
		assertTrue(clientOptional.isPresent(), "The client doesn't exist");
		
		account.setClient(clientOptional.get());

		accountRepository.save(account);
	}

	@Test
	@DisplayName("findById")
	void btest() {
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(), "The account doesn't exist");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(), "The account doesn't exist");		
		
		accountOptional.ifPresent(entity -> {
			entity.setBalance(511124D);
			accountRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(), "The account doesn't exist");	

		accountOptional.ifPresent(entity -> {			
			accountRepository.delete(entity);
		});
	}

}
