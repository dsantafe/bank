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

import co.edu.usbcali.bank.domain.DocumentType;

@SpringBootTest
@Rollback(false)
class DocumentTypeRepositoryTest {

	final static Logger log = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	DocumentTypeRepository documentTypeRepository;

	Long dotyId = 4L;

	@BeforeEach
	void beforeEach() {
		log.info("Se ejecuto el beforeEach");
		assertNotNull(documentTypeRepository, "The documentTypeRepository is null");
	}

	@AfterEach
	void afterEach() {
		log.info("Se ejecuto el afterEach");
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {

		DocumentType documentType = new DocumentType();
		documentType.setDotyId(0L);
		documentType.setName("PASAPORTE");
		documentType.setEnable("S");

		documentType = documentTypeRepository.save(documentType);

		assertNotNull(documentType.getDotyId(), "Fail");
	}

	@Test
	@DisplayName("findById")
	void btest() {
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(), "The document type doesn't exist");
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest() {
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(), "The document type doesn't exist");

		documentTypeOptional.ifPresent(entity -> {
			entity.setEnable("S");
			documentTypeRepository.save(entity);
		});
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest() {
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(), "The document type doesn't exist");

		documentTypeOptional.ifPresent(entity -> {
			documentTypeRepository.delete(entity);
		});
	}
}
