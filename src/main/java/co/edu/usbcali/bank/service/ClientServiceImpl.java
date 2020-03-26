package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.repository.ClientRepository;
import co.edu.usbcali.bank.repository.DocumentTypeRepository;

public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Client> findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Client save(Client entity) throws Exception {
		validate(entity);
		clientRepository.save(entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Client update(Client entity) throws Exception {
		validate(entity);
		clientRepository.save(entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Client entity) throws Exception {
		if (entity == null)
			throw new Exception("The client is null");

		Optional<Client> client = findById(entity.getClieId());
		if(!client.isPresent())
			throw new Exception("The client doesn't exist");
		
		if(client.get().getAccounts().size() > 0)
			throw new Exception("The client have account associate");
		
		if(client.get().getRegisteredAccounts().size() > 0)
			throw new Exception("The client have registered account associate");
		
		clientRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {		
		delete(findById(id).get());
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return clientRepository.count();
	}

	@Override
	public void validate(Client client) throws Exception {

		if (client == null)
			throw new Exception("The client is null");

		Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Client> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}

			throw new Exception(strMessage.toString());
		}
	}

}
