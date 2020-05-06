package users.dao.factory;

import users.dao.ResourceRepository;
import users.dao.UserRepository;
import users.dao.impl.ResourceRepositoryImpl;
import users.dao.impl.UserRepositoryImpl;

public class InMemoryRepoFactory implements AbstractRepoFactory { 

	@Override
	public UserRepository getUserRepository() {
		return new UserRepositoryImpl();
	}

	@Override
	public ResourceRepository getResourceRepository() {
		return new ResourceRepositoryImpl();
	}

}
