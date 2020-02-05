package dynamicproxy;

import javax.inject.Inject;

import org.iproduct.di.annotations.Component;

@Component
public class UserControllerImpl implements UserController {
	@Inject
	public UserRepository repo;

	public UserRepository getRepo() {
		return repo;
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}
	
	
}
