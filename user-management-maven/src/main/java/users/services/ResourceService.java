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
import users.model.Resource;

public interface ResourceService {
	Collection<Resource> getAllResources(); // returns all created Resources in the system;
	Resource getResourceById(long id) throws NonexistingEntityException; // returns the Resource with selected id if such exists, or throws NonexistingEntityException otherwise, with appropriate message;
	Resource createResource(Resource resource) throws InvalidEntityDataException; // adds new resource provided as argument, and returns the Resource with auto-generated id filled in, can throw InvalidEntityDataException when some of the required Resource fields are missing, or when the email or password are invalid (choose your own validation patterns), or when some of the resource ids referred by some Permission are not valid Resource ids;
	Resource updateResource(Resource resource) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException; // updates new resource with provided data (as argument), and returns the updated Resource, or throws NonexistingEntityException with appropriate message if Resource with given id does not exist. Can throw InvalidEntityDataException when some of the reqired Resource fields are missing, or when the password is invalid, or when some of the resource ids referred by some Permission are not valid Resource ids. The email property should be immutable; // should not be allowed to be changed; // in case such attempt is made PropertyChangeNotAllowedException should be thrown with appropriate message;
	Resource deleteResourceById(long id) throws NonexistingEntityException; // deletes the Resource with selected id and returns it, if such Resource exists, or throws NonexistingEntityException otherwise, with appropriate message;
}
