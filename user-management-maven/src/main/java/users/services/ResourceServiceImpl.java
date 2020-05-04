package users.services;

import java.util.Collection;

import users.dao.ResourceRepository;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.Resource;

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
		return resourceRepo.findById(id);
	}

	@Override
	public Resource createResource(Resource resource) throws InvalidEntityDataException {
		return resourceRepo.create(resource);
	}

	@Override
	public Resource updateResource(Resource resource) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		Resource oldResource = resourceRepo.findById(resource.getId());
		return resourceRepo.update(resource);
	}

	@Override
	public Resource deleteResourceById(long id) throws NonexistingEntityException {
		return resourceRepo.removeById(id);
	}

}