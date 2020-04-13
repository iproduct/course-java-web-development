package users.services;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.Resource;

public class ResourceServiceImpl implements ResourceService {
	private static long nextId = 0;
	private List<Resource> resources = new CopyOnWriteArrayList<>();
	
	@Override
	public Collection<Resource> getAllResources() {
		return resources;
	}

	@Override
	public Resource getResourceById(long id) throws NonexistingEntityException {
		return resources.get(getResourceIndexById(id));
	}

	@Override
	public Resource createResource(Resource resource) throws InvalidEntityDataException {
		validateResource(resource);
		resource.setId(++nextId);
		resources.add(resource);
		return resource;
	}

	@Override
	public Resource updateResource(Resource resource) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		validateResource(resource);
		int oldResourceIndex = getResourceIndexById(resource.getId());
		Resource oldResource = resources.get(oldResourceIndex);
		resources.set(oldResourceIndex, resource);
		return resource;
	}

	@Override
	public Resource deleteResourceById(long id) throws NonexistingEntityException {
		int oldResourceIndex = getResourceIndexById(id);
		Resource oldResource = resources.get(oldResourceIndex);
		resources.remove(oldResourceIndex);
		return oldResource;
	}

	
	// Utility methods
	protected int getResourceIndexById(long id) throws NonexistingEntityException {
		for(int i = 0; i < resources.size(); i++) {
			if(resources.get(i).getId() == id) {
				return i;
			}
		}
		throw new NonexistingEntityException(String.format("Resource with ID='%s' does not exist.", id));
	}
	
	protected void validateResource(Resource resource) throws InvalidEntityDataException {
		if(
			   resource.getName() == null || resource.getName().length() < 2
			|| resource.getResourceType() == null
			|| resource.getActions() == null || resource.getActions().size() == 0
		) {
			throw new InvalidEntityDataException("Invalid resource data.");
		}
	}

}
