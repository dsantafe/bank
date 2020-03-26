package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.domain.DocumentType;

@SpringBootTest
@Rollback(false)
class ClientRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	DocumentTypeRepository documentTypeRepository;

	Long clieId = 4040L;

	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
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
		Optional<Client> clientOptional = clientRepository.findById(clieId);
		assertFalse(clientOptional.isPresent(), "The client exist");

		Client client = new Client();
		client.setClieId(clieId);
		client.setAdress("Calle 46");
		client.setEmail("santafe@gmail.com");
		client.setEnable("S");
		client.setName("David Santafe");
		client.setPhone("4895555");

		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(1L);
		assertTrue(documentTypeOptional.isPresent(), "The documentType doesn't exist");

		client.setDocumentType(documentTypeOptional.get());// se obtiene el objeto que devuelve el Optional

		clientRepository.save(client);

		/*
		 * clientOptional.ifPresent(entity -> { log.info("Name: " + entity.getName());
		 * });
		 */
	}

	@Test
	@DisplayName("findById")
	void btest() {
		Optional<Client> clientOptional = clientRepository.findById(clieId);
		assertTrue(clientOptional.isPresent(), "The client doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<Client> clientOptional = clientRepository.findById(clieId);
		assertTrue(clientOptional.isPresent(), "The client doesn't exist");		
		
		clientOptional.ifPresent(entity -> {
			entity.setName("Leidy Valdes");
			clientRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<Client> clientOptional = clientRepository.findById(clieId);

		clientOptional.ifPresent(client -> {
			clientRepository.delete(client);
		});
	}

}
