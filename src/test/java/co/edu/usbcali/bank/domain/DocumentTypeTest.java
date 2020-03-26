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
class DocumentTypeTest {
	
	@PersistenceContext
	EntityManager entityManager;
	
	Long dotyId = 4L;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {		
		assertNotNull(entityManager);
				
		DocumentType documentType = new DocumentType();
		documentType.setDotyId(0L);
		documentType.setName("PASAPORTE");
		documentType.setEnable("S");
		
		documentType = entityManager.merge(documentType);	
		
		documentType = entityManager.find(DocumentType.class, documentType.getDotyId());
		assertNotNull(documentType, "The document type doesn't exist");	
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		DocumentType documentType = entityManager.find(DocumentType.class, dotyId);
		assertNotNull(documentType, "The document type doesn't exist");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		DocumentType documentType = entityManager.find(DocumentType.class, dotyId);
		assertNotNull(documentType, "The document type doesn't exist");	
		
		documentType.setEnable("N");
		
		entityManager.merge(documentType);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		DocumentType documentType = entityManager.find(DocumentType.class, dotyId);
		assertNotNull(documentType, "The document type doesn't exist");			
				
		entityManager.remove(documentType);
	}
}
