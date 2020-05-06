package users;

import static users.view.MenuItem.*;

import static java.util.logging.Level.SEVERE;
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
import static users.model.ResourceType.DIRECTORY;
import static users.model.ResourceType.FILE;
import static users.model.ResourceType.REST_ENDPOINT;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import users.controller.ApplicationController;
import users.exceptions.ActionUnsuccessfulException;
import users.exceptions.InvalidEntityDataException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import users.model.Role;
import users.model.User;
import users.view.AddUserCommand;
import users.view.Command;
import users.view.InputUtilities;
import users.view.MenuItem;

public class MainApplication {
	private static Logger logger = Logger.getLogger(MainApplication.class.getSimpleName());
	private static Scanner sc = new Scanner(System.in);
	private ApplicationController mainController = ApplicationController.getInstance();
	private Map<MenuItem, Command> commands = new HashMap<>(); 
	
	public MainApplication() {
		//Map menu items to commands
		commands.put(ADD_USER, new AddUserCommand(getMainController(), sc));
		commands.put(PRINT_USERS, () -> {
			getMainController().getUserService().getAllUsers().stream().forEach(System.out::println);
		});		
		commands.put(EXIT, this::finish);

	}
	
	public ApplicationController getMainController() {
		return mainController;
	}

	protected void initData() {
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

		Resource[] resources = {
				new Resource("Products endpoint", 1, "/api/products", REST_ENDPOINT, restResourceActions),
				new Resource("Best rated products endpoint", 2, "/api/products/rated", REST_ENDPOINT, restResourceActions),
				new Resource("Most popular products endpoint", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions),
				new Resource("App config file", 1, "/~/api/config/application.properties", FILE, resourceFileActions),
				new Resource("Configuration directory", 1, "/~/api/config", DIRECTORY, resourceDirectoryActions)
		};
		
		Arrays.stream(resources).forEach(r -> {
			try {
				mainController.getResourceService().createResource(r);
			} catch (InvalidEntityDataException e) {
				logger.log(Level.SEVERE, "Error creating resource: " + r, e);
			}
		});
		Arrays.stream(users).forEach(u -> {
			try {
				mainController.getUserService().createUser(u);
			} catch (InvalidEntityDataException e) {
				logger.log(Level.SEVERE, "Error creating user: " + u, e);
			}
		});
	}
	
	public void showMainMenu() {
		String input;
		int choice;
		do {
			System.out.println();
			printMainMenu();
			input = sc.nextLine();
			choice = InputUtilities.paseInt(input, 1, MenuItem.values().length);
			if(choice < 0) {
				System.out.printf("Invalid choice - should be between %d and %d.\n", 1, MenuItem.values().length);
			} else {
				MenuItem selectedItem = MenuItem.values()[choice - 1];
				Command command = commands.get(selectedItem);
				try {
					command.action();
				} catch (ActionUnsuccessfulException e) {
					System.err.println(e.getMessage());
				}
			}
		} while (true);
	}

	private void printMainMenu() {
		for(MenuItem it: MenuItem.values()) {
			System.out.println(String.format("[%d] %s", it.ordinal() + 1, it.getText()));
		}
	}
	
	private void finish() {
		System.out.println("Goodbye from User Manager App!");
		System.exit(0);
	}

	public static void main(String[] args) {
		MainApplication app = new MainApplication();
		app.initData();
//		app.getMainController().getResourceService().getAllResources().stream()
//			.forEach(System.out::println);
//		app.getMainController().getUserService().getAllUsers().stream()
//			.forEach(System.out::println);
		
		app.showMainMenu();
	}
}
