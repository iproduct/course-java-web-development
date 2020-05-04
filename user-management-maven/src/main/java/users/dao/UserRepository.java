package users.dao;

import users.model.User;

//Problem 3.III.2
public interface UserRepository extends GenericRepository<User> {
	User findByEmail(String email);
}
