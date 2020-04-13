package users.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import users.model.Role;
import users.model.User;

public class UserServiceImpl implements UserService {
	private static long nextId = 0;
	private List<User> users = new CopyOnWriteArrayList<>();
	private ResourceService resourceService;

	public UserServiceImpl(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@Override
	public Collection<User> getAllUsers() {
		return users;
	}

	@Override
	public User getUserById(long id) throws NonexistingEntityException {
		return users.get(getUserIndexById(id));
	}

	@Override
	public User getUserByEmail(String email) throws NonexistingEntityException {
		for(User u: users) {
			if(u.getEmail() == email) {
				return u;
			}
		}
		throw new NonexistingEntityException(String.format("User with Email='%s' does not exist.", email));
	}

	@Override
	public User createUser(User user) throws InvalidEntityDataException {
		validateUser(user);
		user.setId(++nextId);
		users.add(user);
		return user;
	}

	@Override
	public User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		validateUser(user);
		int oldUserIndex = getUserIndexById(user.getId());
		User oldUser = users.get(oldUserIndex);
		if(!user.getEmail().equals(oldUser.getEmail())) {
			throw new PropertyChangeNotAllowedException("Property 'email' can not be changed.");
		}
		users.set(oldUserIndex, user);
		return user;
	}

	@Override
	public User deleteUserById(long id) throws NonexistingEntityException {
		int oldUserIndex = getUserIndexById(id);
		User oldUser = users.get(oldUserIndex);
		users.remove(oldUserIndex);
		return oldUser;
	}

	@Override
	public Set<Permission> getUserPermissions(User user) {
		Set<Permission> permissions = new HashSet<>();
		for(Role role: user.getRoles()) {
			permissions.addAll(role.getPermissions());
		}
		permissions.addAll(user.getPermissions());
		return permissions;
	}

	@Override
	public boolean checkUserPermission(User user, Permission permission) {
		return getUserPermissions(user).contains(permission);
	}

	@Override
	public boolean checkUserPermission(User user, Resource resource, ActionType actionType) {
		return checkUserPermission(user, new Permission("", resource.getResourceType(), resource.getId(), actionType));
	}

	
	// Utility methods
	protected int getUserIndexById(long id) throws NonexistingEntityException {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == id) {
				return i;
			}
		}
		throw new NonexistingEntityException(String.format("User with ID='%s' does not exist.", id));
	}
	
	protected void validateUser(User user) throws InvalidEntityDataException {
		if(user.getEmail() == null || user.getEmail().length() < 5) {
				throw new InvalidEntityDataException("Invalid user email.");
		}
		if(user.getFirstName() == null || user.getFirstName().length() < 2) {
				throw new InvalidEntityDataException("Invalid user first name.");
		}
		if(user.getLastName() == null || user.getLastName().length() < 2) {
				throw new InvalidEntityDataException("Invalid user last name.");
		}
		if(user.getRoles() == null || user.getRoles().size() == 0) {
				throw new InvalidEntityDataException("Invalid user roles.");
		}
		if(user.getPassword() == null || user.getPassword().length() < 5) {
				throw new InvalidEntityDataException("Invalid user password.");
		}
		for(Permission p: user.getPermissions()) {
			try {
				resourceService.getResourceById(p.getResourceId());
			} catch (NonexistingEntityException ex) {
				throw new InvalidEntityDataException(String.format("Error in User permissions: %s", ex.getMessage()));
			}
			
		}
	}

}
