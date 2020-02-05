package dynamicproxy;

public interface UserController {
	UserRepository getRepo();
	void setRepo(UserRepository repo);
}
