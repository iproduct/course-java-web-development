package dynamicproxy;

import javax.inject.Inject;

import org.iproduct.di.annotations.Component;
import org.iproduct.di.annotations.JaxbRepository;

@Component
public class UserControllerImpl implements UserController {
	@Inject @JaxbRepository
	private UserRepository repo;

	public UserRepository getRepo() {
		return repo;
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}
	
}
