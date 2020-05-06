package users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static users.model.ActionType.*;

import users.dao.ResourceRepository;
import users.dao.UserRepository;
import users.dao.factory.AbstractRepoFactory;
import users.dao.factory.RepositoryFactoryProvider;
import users.dao.factory.RepositoryType;
import users.dao.impl.JAXBEntityRepository;
import users.dao.impl.ResourceRepositoryImpl;
import users.dao.impl.UserRepositoryImpl;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import static users.model.ResourceType.*;
import users.model.Role;
import users.model.User;
import users.services.LoginService;
import users.services.LoginServiceImpl;
import users.services.ResourceService;
import users.services.ResourceServiceImpl;
import users.services.UserService;
import users.services.UserServiceImpl;

public class UsersSimpleDemo {
	private static Logger logger = Logger.getLogger(UsersSimpleDemo.class.getSimpleName());

	public UsersSimpleDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Set<ActionType> restResourceActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
				CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, UPDATE_OWN, READ_OWN, DELETE_OWN
		}));
		Set<ActionType> resourceFileActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
				CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, WRITE_ALL, UPDATE_OWN, READ_OWN, WRITE_OWN, DELETE_OWN, EXECUTE
		}));
		Set<ActionType> resourceDirectoryActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
				CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, UPDATE_OWN, READ_OWN, DELETE_OWN, WRITE_ALL, WRITE_OWN, 
		}));
//		Set<Role> customerRoleSet = new HashSet<>(Arrays.asList(new Role[] {
//				Role.ROLE_ADMIN, Role.ROLE_CUSTOMER, Role.ROLE_MANAGER
//		}));
		User[] users = {
				new User(1, "Ivan", "Petrov", "ivan@abv.bg", "ivan123", true, Role.ROLE_ADMIN, 
						new HashSet<Permission>(), new Date(), new Date()),
				new User(2, "John", "Smith", "johny123@gmail.com", "john123", true, Role.ROLE_CUSTOMER, 
						new HashSet<Permission>(), new Date(), new Date()),
				new User(3, "Dimitar", "Petrov", "dimitar@gmail.com", "dimitar", true, Role.ROLE_MANAGER, 
						new HashSet<Permission>(), new Date(), new Date()),
				new User(4, "Hristo", "Petrov", "hristo@abv.bg", "hristo", true, Role.ROLE_ADMIN, 
						new HashSet<Permission>(), new Date(), new Date()),
				new User(5, "Maria", "Georgieva", "maria@abv.bg", "maria", true, Role.ROLE_ADMIN, 
						new HashSet<Permission>(), new Date(), new Date())
		};
		System.out.println(users);
		Resource[] resources = {
				new Resource("Products endpoint", 1, "/api/products", REST_ENDPOINT, restResourceActions),
				new Resource("Best rated products endpoint", 2, "/api/products/rated", REST_ENDPOINT, restResourceActions),
				new Resource("Most popular products endpoint", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions),
				new Resource("App config file", 1, "/~/api/config/application.properties", FILE, resourceFileActions),
				new Resource("Configuration directory", 1, "/~/api/config", DIRECTORY, resourceDirectoryActions)
		};
		System.out.println(resources);
		
//		// Test PermissionChangeListener - Problem 1.V.5
//		System.out.println(users[0]);
//		Arrays.stream(users).forEach(u -> u.addUserPermissionChangeListener(event -> { System.out.println(event); }));
//		Set<Permission> oldPermissions = users[0].getPermissions();
//		Set<Permission> newPermisssions = new HashSet<>(oldPermissions);
//		newPermisssions.add(new Permission("Config file permsiion", FILE, READ_ALL));
//		users[0].setPermissions(newPermisssions);
//		System.out.println("After:" + users[0]);
//		
//		// Test PropertyChangeListener - Problem 1.V.6
//		System.out.println(resources[0]);
//		Arrays.stream(resources).forEach(p -> p.addPropertyChangeListener(event -> { System.out.println(event); }));
//		String oldName = resources[0].getName();
//		String newName = "Changed " + oldName;
//		resources[0].setName(newName);
//		System.out.println("After:" + resources[0]);
		
		// Test UserService - Problem 2.I.1 and 2.II.1, Problem 3.VII, 
//		ResourceRepository resourceRepo = new ResourceRepositoryImpl();
//		UserRepository userRepo = new UserRepositoryImpl();
		
		// read filenames from property file
		URL appConfigPath = UsersSimpleDemo.class.getClassLoader().getResource("application.properties");
		System.out.println("Configuration file: " + appConfigPath);
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(appConfigPath.getPath()));
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Configuration file not found: " + appConfigPath + ".");
		} catch (IOException e1) {
			logger.log(Level.SEVERE, "Error reading configuration file: " + appConfigPath + ".");
		}
		
		//Problem 4.II
		AbstractRepoFactory repoFactory = RepositoryFactoryProvider.getRepositoryFactory(RepositoryType.XML_FILE, properties);
		ResourceRepository resourceRepo = repoFactory.getResourceRepository();
		UserRepository userRepo = repoFactory.getUserRepository();
		
		// Create resources
		ResourceService resourceService = new ResourceServiceImpl(resourceRepo);
		Arrays.stream(resources).map(r -> {
			try {
				return resourceService.createResource(r);
			} catch (InvalidEntityDataException e) {
				e.printStackTrace();
			}
			return null;
		}).forEach(System.out::println);
		
		System.out.println("\nCreated resources:");
		resourceService.getAllResources().stream().forEach(System.out::println);
		
		System.out.println("\n");

		// Create users
		UserService userService = new UserServiceImpl(userRepo, resourceService);
		Arrays.stream(users).map(u -> {
			try {
				return userService.createUser(u);
			} catch (InvalidEntityDataException e) {
				e.printStackTrace();
			}
			return null;
		}).forEach(System.out::println); // users ivan and john are invalid because password is less then 5 chars long
		
		System.out.println("\nCreated users:");
		userService.getAllUsers().stream().forEach(System.out::println);
		
		String testEmail = "dimitar@gmail.com";
		try {
			System.out.printf("%nUser with Email='%s':%s%n", testEmail, userService.getUserByEmail(testEmail));
		} catch (NonexistingEntityException e) {
			e.printStackTrace();
		}
		
		try {
			long deleteId = userService.getUserByEmail(testEmail).getId();
			System.out.printf("%nDeleting User with ID='%d':%s%n", deleteId, userService.deleteUserById(deleteId));
		} catch (NonexistingEntityException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nUser after delete:");
		userService.getAllUsers().stream().forEach(System.out::println);
		
		// Test LoginService - Problem 2.I.2 and 2.II.2
		LoginService loginService = new LoginServiceImpl(userService);
		String loginEmail = "maria@abv.bg";
		String invalidPass = "invalid_pass";
		String validPass = "maria";
		System.out.printf("\nTry to login with email: %s, and password: %s --> %b%n", 
				loginEmail, invalidPass, loginService.login(loginEmail, invalidPass) );
		System.out.printf("\nTry to login with email: %s, and password: %s --> %b%n", 
				loginEmail, validPass, loginService.login(loginEmail, validPass) );
		System.out.printf("\nLogged User: %s%n", loginService.getLoggedUser());
	}

}
