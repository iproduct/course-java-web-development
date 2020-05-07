package users.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import users.dao.UserRepository;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import users.model.Role;
import users.model.User;

public class UserServiceImpl implements UserService {
	private UserRepository userRepo;
	private ResourceService resourceService;

	public UserServiceImpl(UserRepository userRepo, ResourceService resourceService) {
		this.userRepo = userRepo;
		this.resourceService = resourceService;
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(long id) throws NonexistingEntityException {
		User existing = userRepo.findById(id);
		if(existing == null) {
			throw new NonexistingEntityException("User with ID='" + id + " does not exists!");
		}
		return existing;
	}

	@Override
	public User getUserByEmail(String email) throws NonexistingEntityException {
		Optional<User> result = Optional.ofNullable(userRepo.findByEmail(email));
		return result.orElseThrow(
				() -> new NonexistingEntityException(String.format("User with Email='%s' does not exist.", email)));
	}

	@Override
	public User createUser(User user) throws InvalidEntityDataException {
		validateUser(user);
		return userRepo.create(user);
	}

	@Override
	public User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		User oldUser = userRepo.findById(user.getId());
		if(oldUser == null) {
			throw new NonexistingEntityException("User with ID='" + user.getId() + " does not exists!");
		}
		if(!oldUser.getEmail().equals(user.getEmail())) { 
			throw new PropertyChangeNotAllowedException("Property 'email' can not be changed.");
		}
		validateUser(user);

		return userRepo.update(user);
	}

	@Override
	public User deleteUserById(long id) throws NonexistingEntityException {
		User existing = userRepo.removeById(id);
		if(existing == null) {
			throw new NonexistingEntityException("User with ID='" + id + " does not exists!");
		}
		return existing;
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

	protected void validateUser(User user) throws InvalidEntityDataException {
//		if(user.getEmail() == null || user.getEmail().length() < 5) {
//				throw new InvalidEntityDataException("Invalid user email.");
//		}
//		if(user.getFirstName() == null || user.getFirstName().length() < 2) {
//				throw new InvalidEntityDataException("Invalid user first name.");
//		}
//		if(user.getLastName() == null || user.getLastName().length() < 2) {
//				throw new InvalidEntityDataException("Invalid user last name.");
//		}
//		if(user.getRoles() == null || user.getRoles().size() == 0) {
//				throw new InvalidEntityDataException("Invalid user roles.");
//		}
//		if(user.getPassword() == null || user.getPassword().length() < 5) {
//				throw new InvalidEntityDataException("Invalid user password.");
//		}
		for(Permission p: user.getPermissions()) {
			if(p.getResourceId() != null) {
				try {
					resourceService.getResourceById(p.getResourceId());
				} catch (NonexistingEntityException ex) {
					throw new InvalidEntityDataException(String.format("Error in User permissions: %s", ex.getMessage()));
				}
			}
		}
	}

}
