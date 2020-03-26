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

import co.edu.usbcali.bank.domain.TransactionType;

@SpringBootTest
@Rollback(false)
class TransactionTypeRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	TransactionTypeRepository transactionTypeRepository;

	Long trtyId = 4L;

	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
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
		
		TransactionType transactionType = new TransactionType();
		transactionType.setTrtyId(0L);
		transactionType.setName("ABONO");
		transactionType.setEnable("S");
		
		transactionTypeRepository.save(transactionType);
	}

	@Test
	@DisplayName("findById")
	void btest() {
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(), "The transaction type doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(), "The transaction type doesn't exist");			
		
		transactionTypeOptional.ifPresent(entity -> {
			entity.setEnable("S");
			transactionTypeRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(), "The transaction type doesn't exist");		

		transactionTypeOptional.ifPresent(entity -> {
			transactionTypeRepository.delete(entity);
		});
	}	
	
}
