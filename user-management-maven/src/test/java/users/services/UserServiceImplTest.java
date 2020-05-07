package users.services;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static users.model.ActionType.CREATE;
import static users.model.ActionType.DELETE_ALL;
import static users.model.ActionType.DELETE_OWN;
import static users.model.ActionType.EXECUTE;
import static users.model.ActionType.READ_ALL;
import static users.model.ActionType.READ_OWN;
import static users.model.ActionType.UPDATE_ALL;
import static users.model.ActionType.UPDATE_OWN;
import static users.model.ActionType.WRITE_ALL;
import static users.model.ActionType.WRITE_OWN;
import static users.model.ResourceType.FILE;
import static users.model.ResourceType.REST_ENDPOINT;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import users.dao.ResourceRepository;
import users.dao.UserRepository;
import users.dao.impl.ResourceRepositoryImpl;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import users.model.Role;
import users.model.User;

public class UserServiceImplTest {
	static final Set<ActionType> restResourceActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
			CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, UPDATE_OWN, READ_OWN, DELETE_OWN
	}));
	static final Set<ActionType> resourceFileActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
			CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, WRITE_ALL, UPDATE_OWN, READ_OWN, WRITE_OWN, DELETE_OWN, EXECUTE
	}));
	static final Set<ActionType> resourceDirectoryActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
			CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, UPDATE_OWN, READ_OWN, DELETE_OWN, WRITE_ALL, WRITE_OWN, 
	}));
	static final User[] users = {
			new User(1L, "Ivan", "Petrov", "ivan@abv.bg", "ivan123", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date()),
			new User(2L, "John", "Smith", "johny123@gmail.com", "john123", true, Role.ROLE_CUSTOMER, 
					new HashSet<Permission>(), new Date(), new Date()),
			new User(3L, "Dimitar", "Petrov", "dimitar@gmail.com", "dimitar", true, Role.ROLE_MANAGER, 
					new HashSet<Permission>(), new Date(), new Date()),
			new User(4L, "Hristo", "Petrov", "hristo@abv.bg", "hristo", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date()),
			new User(5L, "Maria", "Georgieva", "maria@abv.bg", "maria", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date())
	};
	
	static final User newUser = 
			new User("Maria", "Georgieva", "maria@abv.bg", "maria", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date());
			
	static final User invalidUser = 
			new User("Maria", "Georgieva", "maria", "maria", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date());
	
	static final User updatedUser = 
			new User(5L, "Maria", "Petrova", "maria@abv.bg", "maria", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date());
			
	static final User invalidUserEmailUpdate = 
			new User(5L, "Maria", "Georgieva", "m.georgieva@gmail.com", "maria", true, Role.ROLE_ADMIN, 
					new HashSet<Permission>(), new Date(), new Date());
	
	static final Resource fileResource = 
			new Resource(4L, "App config file", 1, "/~/api/config/application.properties", FILE, resourceFileActions);
	
	static final Permission fileResourcePermision = new Permission("Config file permsiion", FILE, fileResource.getId(), READ_ALL);
	
	static final User userPermissionResourceUpdate = 
			new User(5L, "Maria", "Georgieva", "maria@abv.bg", "maria", true, Role.ROLE_ADMIN, 
				new HashSet<Permission>(Arrays.asList(fileResourcePermision)),
				new Date(), new Date());
	
	static final Long invalidResourceId = 1000L;
	static final User invalidUserPermissionResourceUpdate = 
			new User(5L, "Maria", "Georgieva", "maria@abv.bg", "maria", true, Role.ROLE_ADMIN, 
				new HashSet<Permission>(Arrays.asList(new Permission("Config file permsiion", FILE, invalidResourceId, READ_ALL))),
				new Date(), new Date());
	

	@Mock
	private UserRepository userRepo;
	
	@Mock
	private ResourceService resourceService ;
	
	private UserServiceImpl service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UserServiceImpl(userRepo, resourceService);
		
//		// Stubbing ResourceService dependency
//		ResourceRepository resourceRepo = new ResourceRepositoryImpl();
//		resourceService = new ResourceServiceImpl(resourceRepo);
		
		// Creating the service under test
		service = new UserServiceImpl(userRepo, resourceService);
	}
	
	
	// Tests
	
	@Test
	public void getAllUsers_ReturnUsers_UsersExist() {
		Mockito.when(userRepo.findAll()).thenReturn(Arrays.asList(users));
		assertEquals(Arrays.asList(users), service.getAllUsers());
	}

	@Test
	public void getUserById_ReturnUserWithId_UserExists() throws NonexistingEntityException {
		final long testId = 4L;
		Mockito.when(userRepo.findById(testId)).thenReturn(users[3]);
		assertEquals(users[3], service.getUserById(testId));
	}

	@Test(expected = NonexistingEntityException.class)
	public void getUserById_ThrowNonexistingEntityException_UserDoesNotExist() throws NonexistingEntityException {
		final long testId = 7L;
		Mockito.when(userRepo.findById(testId)).thenReturn(null);
		service.getUserById(testId);
	}
	
	@Test
	public void getUserByEmail_ReturnUserWithId_UserExists() throws NonexistingEntityException {
		final String testEmail = users[3].getEmail();
		Mockito.when(userRepo.findByEmail(testEmail)).thenReturn(users[3]);
		assertEquals(users[3], service.getUserByEmail(testEmail));
	}

	@Test(expected = NonexistingEntityException.class)
	public void getUserByEmail_ThrowNonexistingEntityException_UserDoesNotExist() throws NonexistingEntityException {
		final String testEmail = invalidUserEmailUpdate.getEmail();
		Mockito.when(userRepo.findByEmail(testEmail)).thenReturn(null);
		service.getUserByEmail(testEmail);
	}
	
	@Test
	public void createUser_ReturnCreatedUser_UserDoesNotExist() throws InvalidEntityDataException {
		Mockito.when(userRepo.create(newUser)).thenReturn(users[4]);
		assertEquals(users[4], service.createUser(newUser));
	}

	@Test(expected = InvalidEntityDataException.class)
	public void createUser_ThrowInvalidEntityDataException_UserDataInvalid() throws InvalidEntityDataException {
		Mockito.when(userRepo.create(invalidUser)).thenThrow(new InvalidEntityDataException());
		service.createUser(invalidUser);
	}

	@Test
	public void updateUser_ReturnUpdatedUser_UserValid() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		final long testId = 5L;
		Mockito.when(userRepo.findById(testId)).thenReturn(users[4]);
		Mockito.when(userRepo.update(updatedUser)).thenReturn(updatedUser);
		assertEquals(updatedUser, service.updateUser(updatedUser));
	}
	
	@Test
	public void updateUser_ReturnUpdatedUser_UserPermisionResourceIdValid() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		final long testId = userPermissionResourceUpdate.getId();
		Mockito.when(userRepo.findById(testId)).thenReturn(userPermissionResourceUpdate);
		Mockito.when(resourceService.getResourceById(fileResource.getId())).thenReturn(fileResource);
		Mockito.when(userRepo.update(userPermissionResourceUpdate)).thenReturn(userPermissionResourceUpdate);
		assertEquals(userPermissionResourceUpdate, service.updateUser(userPermissionResourceUpdate));
	}
	
	@Test(expected = InvalidEntityDataException.class)
	public void updateUser_ThrowInvalidEntityDataException_UserPermisionResourceIdInvalid() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		final long testId = userPermissionResourceUpdate.getId();
		Mockito.when(userRepo.findById(testId)).thenReturn(userPermissionResourceUpdate);
		Mockito.when(resourceService.getResourceById(invalidResourceId))
			.thenThrow(new NonexistingEntityException("Resource with ID='" + invalidResourceId + "' does not exists!"));
		Mockito.when(userRepo.update(invalidUserPermissionResourceUpdate)).thenReturn(invalidUserPermissionResourceUpdate);
		service.updateUser(invalidUserPermissionResourceUpdate);
	}
	
	@Test(expected = NonexistingEntityException.class)
	public void updateUser_ThrowNonexistingEntityException_UserDoesNotExis() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		Mockito.when(userRepo.findById(users[4].getId())).thenReturn(null);
		Mockito.when(userRepo.update(users[4])).thenThrow(new NonexistingEntityException());
		service.updateUser(users[4]);
	}
	
	@Test(expected = PropertyChangeNotAllowedException.class)
	public void updateUser_throwPropertyChangeNotAllowedException_InvalidEmailChange() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		Mockito.when(userRepo.findById(invalidUserEmailUpdate.getId())).thenReturn(users[4]);
		Mockito.when(userRepo.update(invalidUserEmailUpdate)).thenReturn(invalidUserEmailUpdate);
		service.updateUser(invalidUserEmailUpdate);
	}

	@Test
	public void deleteUserById_ReturnUserWithId_UserExists() throws NonexistingEntityException {
		final long testId = 4L;
		Mockito.when(userRepo.removeById(testId)).thenReturn(users[3]);
		assertEquals(users[3], service.deleteUserById(testId));
	}

	@Test(expected = NonexistingEntityException.class)
	public void deleteUserById_ThrowNonexistingEntityException_UserDoesNotExist() throws NonexistingEntityException {
		final long testId = 7L;
		Mockito.when(userRepo.removeById(testId)).thenReturn(null);
		service.deleteUserById(testId);
	}

	@Test
	public void getUserPermissions_ReturnRolePermissions_OnlyRolePermissionsAssigned() {
		assertEquals(users[4].getRoles().toArray(new Role[] {}).clone()[0].getPermissions(), service.getUserPermissions(users[4]));
	}

	@Test
	public void checkUserPermission_ReturnTrue_ForIndividualResourcePermission() {
		assertEquals(true, service.checkUserPermission(userPermissionResourceUpdate, fileResourcePermision));
	}
	
	@Test
	public void checkUserPermission_ReturnTrue_ForAllAdminPermisiions() {
		Role.adminRolePermissions.stream().forEach(permission -> 
			assertEquals(true, service.checkUserPermission(userPermissionResourceUpdate, fileResourcePermision)));
	}

	@Test
	public void checkUserPermission_ReturnTrue_ForExistingFilePermisiion() {
		assertEquals(true, service.checkUserPermission(userPermissionResourceUpdate, fileResource, ActionType.READ_ALL));

	}
	@Test
	public void checkUserPermission_ReturnFalse_ForNonExistingFilePermisiion() {
		assertEquals(false, service.checkUserPermission(userPermissionResourceUpdate, fileResource, ActionType.CREATE));

	}

}
