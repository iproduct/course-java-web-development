package users.dao.factory;

import users.dao.ResourceRepository;
import users.dao.UserRepository;

public interface AbstractRepoFactory {
	UserRepository getUserRepository();
	ResourceRepository getResourceRepository();
}
