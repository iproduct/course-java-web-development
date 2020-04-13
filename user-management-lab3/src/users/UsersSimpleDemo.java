package users;

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
		Set<ActionType> restResourceAdminActions = new HashSet<ActionType>();
		restResourceAdminActions.add(CREATE);
		restResourceAdminActions.add(UPDATE_ALL);
		restResourceAdminActions.add(READ_ALL);
		restResourceAdminActions.add(DELETE_ALL);
		Set<Role> customerRoleSet = new HashSet<>();
		User[] users = {
				new User(1, "Ivan", "Petrov", "ivan@abv.bg", "ivan", true, Role.ROLE_ADMIN, 
						new HashSet<Permission>(), new Date(), new Date())
		};
		Resource[] resources = {
				new Resource("Products endpoint", users[0], "/api/products", REST_ENDPOINT, restResourceAdminActions)
		};
		
		System.out.println(users[0]);
		users[0].addUserPermissionChangeListener(event -> {System.out.println(event);});
		Set<Permission> oldPermissions = users[0].getPermissions();
		Set<Permission> newPermisssions = new HashSet(oldPermissions);
		newPermisssions.add(new Permission("Config file permsiion", FILE, READ_ALL));
		users[0].setPermissions(newPermisssions);
		System.out.println("After:" + users[0]);
		
		System.out.println(resources[0]);

	}

}
