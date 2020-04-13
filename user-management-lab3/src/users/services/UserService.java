package users.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import users.model.User;

public interface UserService {
	Collection<User> getAllUsers(); // returns all created Users in the system;
	User getUserById(long id) throws NonexistingEntityException; // returns the User with selected id if such exists, or throws NonexistingEntityException otherwise, with appropriate message;
	User getUserByEmail(String email) throws NonexistingEntityException; // returns the User with selected email if such exists, or throws NonexistingEntityException otherwise, with appropriate message;
	User createUser(User user) throws InvalidEntityDataException; // adds new user provided as argument, and returns the User with auto-generated id filled in, can throw InvalidEntityDataException when some of the required User fields are missing, or when the email or password are invalid (choose your own validation patterns), or when some of the resource ids referred by some Permission are not valid Resource ids;
	User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException; // updates new user with provided data (as argument), and returns the updated User, or throws NonexistingEntityException with appropriate message if User with given id does not exist. Can throw InvalidEntityDataException when some of the reqired User fields are missing, or when the password is invalid, or when some of the resource ids referred by some Permission are not valid Resource ids. The email property should be immutable; // should not be allowed to be changed; // in case such attempt is made PropertyChangeNotAllowedException should be thrown with appropriate message;
	User deleteUserById(long id) throws NonexistingEntityException; // deletes the User with selected id and returns it, if such User exists, or throws NonexistingEntityException otherwise, with appropriate message;
	Set<Permission> getUserPermissions(User user); // for given User returns a collection of Permission objects, representing all permissions given to the User. Implemented business logic should take into account the possibility for permission overriding of Permissions provided by Role, by the same permission types provided on User instance level;
	boolean checkUserPermission(User user, Permission permission); // for given User and Permission arguments the method returns boolean; // true if the User has given Permission, and false if not.
	boolean checkUserPermission(User user, Resource resource, ActionType actionType); // for given User, Resource, and ActionType arguments the method returns boolean; // true if the User has Permission to execute requested ActionType on given Resource, and false if not.
}
