package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest	//conecta junit con spring
@Rollback(false)
class ClientTest {

	@PersistenceContext
	EntityManager entityManager;
	
	Long clieId = 4040L;//L mayus para especificar que es Long
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest() {
		//fail("Not yet implemented");
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);		
		assertNull(client,"El cliente ya existe");
		
		client = new Client();
		client.setClieId(clieId);
		client.setAdress("Calle 46");
		client.setEmail("santafe@gmail.com");
		client.setEnable("S");
		client.setName("David Santafe");
		client.setPhone("4895555");
		
		DocumentType documentType = entityManager.find(DocumentType.class, 1L);
		assertNotNull(documentType,"El tipo de documento no existe.");		
		
		client.setDocumentType(documentType);
		
		entityManager.persist(client);		
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);		
		assertNotNull(client,"El cliente no existe");		
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void ctest() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);		
		assertNotNull(client,"El cliente no existe");		
		
		//sino detecta un cambio en el objeto no lanza el update
		//patron: proxy
		client.setName("Leidy Valdes");
		
		entityManager.merge(client);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dtest() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);		
		assertNotNull(client,"El cliente no existe");		
				
		entityManager.remove(client);
	}
}
