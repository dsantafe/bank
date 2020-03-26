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
import co.edu.usbcali.bank.domain.RegisteredAccount;

@SpringBootTest	
@Rollback(false)
class RegisteredAccountRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	RegisteredAccountRepository registeredAccountRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ClientRepository clientRepository;

	String accoId = "0000-0000-0000-0000";
	Long clieId = 4040L;
	Long reacId = 9L;
	
	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(registeredAccountRepository, "The registeredAccountRepository is null");
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
		
		RegisteredAccount registeredAccount = new RegisteredAccount();
		registeredAccount.setReacId(0L);
		registeredAccount.setEnable("S");
		
		Optional<Client> clientOptional = clientRepository.findById(clieId);
		assertTrue(clientOptional.isPresent(), "The client doesn't exist");
		
		registeredAccount.setClient(clientOptional.get());
		
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(), "The account doesn't exist");
		
		registeredAccount.setAccount(accountOptional.get());

		registeredAccountRepository.save(registeredAccount);
	}

	@Test
	@DisplayName("findById")
	void btest() {
		Optional<RegisteredAccount> registeredAccountOptional = registeredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(), "The registered account doesn't exist");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<RegisteredAccount> registeredAccountOptional = registeredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(), "The registered account doesn't exist");	
		
		registeredAccountOptional.ifPresent(entity -> {
			entity.setEnable("N");
			registeredAccountRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<RegisteredAccount> registeredAccountOptional = registeredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(), "The registered account doesn't exist");	

		registeredAccountOptional.ifPresent(entity -> {			
			registeredAccountRepository.delete(entity);
		});
	}

}
