package users;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static users.model.ActionType.*;

import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import static users.model.ResourceType.*;
import users.model.Role;
import users.model.User;

public class UsersSimpleDemo {

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
		Set<Role> customerRoleSet = new HashSet<>(Arrays.asList(new Role[] {
				Role.ROLE_ADMIN, Role.ROLE_CUSTOMER, Role.ROLE_MANAGER
		}));
		User[] users = {
				new User(1, "Ivan", "Petrov", "ivan@abv.bg", "ivan", true, Role.ROLE_ADMIN, 
						new HashSet<Permission>(), new Date(), new Date()),
				new User(2, "John", "Smith", "johny123@gmail.com", "john", true, Role.ROLE_CUSTOMER, 
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
				new Resource("Products endpoint1", users[0], "/api/products", REST_ENDPOINT, restResourceActions),
				new Resource("Products endpoint2", users[0], "/api/products", REST_ENDPOINT, restResourceActions),
				new Resource("Products endpoint3", users[0], "/api/products", REST_ENDPOINT, restResourceActions),
				new Resource("App config file", users[0], "/~/api/config/application.properties", FILE, resourceFileActions),
				new Resource("Configuration directory", users[0], "/~/api/config", DIRECTORY, resourceDirectoryActions)
		};
		System.out.println(resources);
		
		// Test PermissionChangeListener - Problem 1.V.5
		System.out.println(users[0]);
		Arrays.stream(users).forEach(u -> u.addUserPermissionChangeListener(event -> { System.out.println(event); }));
		Set<Permission> oldPermissions = users[0].getPermissions();
		Set<Permission> newPermisssions = new HashSet(oldPermissions);
		newPermisssions.add(new Permission("Config file permsiion", FILE, READ_ALL));
		users[0].setPermissions(newPermisssions);
		System.out.println("After:" + users[0]);
		
		// Test PropertyChangeListener - Problem 1.V.6
		System.out.println(resources[0]);
		Arrays.stream(resources).forEach(p -> p.addPropertyChangeListener(event -> { System.out.println(event); }));
		String oldName = resources[0].getName();
		String newName = "Changed " + oldName;
		resources[0].setName(newName);
		System.out.println("After:" + resources[0]);
	}

}
