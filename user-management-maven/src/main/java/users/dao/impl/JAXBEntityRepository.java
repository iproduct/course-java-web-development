package users.dao.impl;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import users.dao.GenericRepository;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.model.Entities;
import users.model.Identifiable;

//Problem 4.I
public class JAXBEntityRepository<T extends Identifiable> implements GenericRepository<T> {
	private static long nextId = 0;
	private static Logger logger = Logger.getLogger(JAXBEntityRepository.class.getSimpleName());
	protected File file;
	protected JAXBContext jaxbContext;
	protected Validator validator;

	protected Map<Long, T> entities = new ConcurrentHashMap<>();

	public JAXBEntityRepository(String xmlFileName) {
		file = new File(xmlFileName);
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Override
	public Collection<T> findAll() {
		refresh();
		return entities.values();
	}

	@Override
	public T findById(long id) throws NonexistingEntityException {
		if(entities.isEmpty()) {
			refresh();
		}
		return entities.get(id);
	}

	@Override
	public long count() {
		if(entities.isEmpty()) {
			refresh();
		}
		return entities.size();
	}

	@Override
	public T create(T entity) throws InvalidEntityDataException {
		T exisitng = entities.get(entity.getId());
		if(exisitng != null) {
			throw new InvalidEntityDataException("Entity with ID='" + entity.getId() 
			+ " already exists!");
		}

		entity.setId(getNextId());
		
		// use HybernateValidator
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		if(!violations.isEmpty()) {
			throw new InvalidEntityDataException("Invalid entity data:" 
					+ violations.stream().map(cv -> String.format("%s %s: '%s' - %s.", 
					cv.getLeafBean().getClass().getSimpleName(), cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()))
						.reduce("", (str, cvStr) -> str + " " + cvStr));
		}
		
		entities.put(entity.getId(), entity);
		
		// serialize all entites to XML file
		try {
			serializeToXml(new Entities<T>(entities.values()));
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, "Error serializing to XML file " + file + ": ", e);
		}
		return entity;
	}

	@Override
	public T update(T entity) throws NonexistingEntityException {
		long id = entity.getId();
		T exisitng = entities.get(id);
		if(exisitng == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		entities.put(id, entity);
		
		// serialize all entites to XML file
		try {
			serializeToXml(new Entities<T>(entities.values()));
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, "Error serializing to XML file " + file + ": ", e);
		}
		return entity;
	}

	@Override
	public T removeById(long id) throws NonexistingEntityException {
		T existing = entities.remove(id);
		if(existing == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		
		// serialize all entites to XML file
		try {
			serializeToXml(new Entities<T>(entities.values()));
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, "Error serializing to XML file " + file + ": ", e);
		}
		return existing;
	}
	
	protected long getNextId( ) {
		return ++nextId;
	}

	// protected and private methods
	protected void serializeToXml(Entities<T> entities) throws JAXBException {
		if(jaxbContext == null) {
			jaxbContext = JAXBContext.newInstance("users.model");
		}
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(entities, file);
	}

	protected Entities<T> deserializeFromXml() throws JAXBException {
		if(jaxbContext == null) {
			jaxbContext = JAXBContext.newInstance("users.model");
		}
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Entities<T>) jaxbUnmarshaller.unmarshal(file);
	}
	
	protected void refresh() {
		try {
			entities.clear();
			Collection<T> result = deserializeFromXml().getEntities();
			result.stream()
				.forEach(entity -> entities.put(entity.getId(), entity));
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, "Error deserializing from XML file " + file + ": ", e);
		}
	}

}
