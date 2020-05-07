package users.services;

import java.util.Collection;

import users.dao.ResourceRepository;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.Resource;
import users.model.User;

public class ResourceServiceImpl implements ResourceService {
	private ResourceRepository resourceRepo;

	public ResourceServiceImpl(ResourceRepository resourceRepo) {
		this.resourceRepo = resourceRepo;
	}

	@Override
	public Collection<Resource> getAllResources() {
		return resourceRepo.findAll();
	}

	@Override
	public Resource getResourceById(long id) throws NonexistingEntityException {
		Resource existing = resourceRepo.findById(id);
		if(existing == null) {
			throw new NonexistingEntityException("Resource with ID='" + id + " does not exists!");
		}
		return existing;
	}

	@Override
	public Resource createResource(Resource resource) throws InvalidEntityDataException {
		return resourceRepo.create(resource);
	}

	@Override
	public Resource updateResource(Resource resource) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		return resourceRepo.update(resource);
	}

	@Override
	public Resource deleteResourceById(long id) throws NonexistingEntityException {
		Resource existing = resourceRepo.removeById(id);
		if(existing == null) {
			throw new NonexistingEntityException("Resource with ID='" + id + " does not exists!");
		}
		return existing;
	}

}