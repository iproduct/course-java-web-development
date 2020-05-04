package users.view;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import users.model.Role;
import users.model.User;


public class InputUtilities {
	
	public static User inputUser(Scanner sc) {
		users.model.User u = new User();
		String input;
//		"firstName",
//	    "lastName",
//	    "email",
//	    "password",
//	    "active",
//	    "roles",
//	    "permissions",
//	    "created",
//	    "modified"
		do {
			System.out.println("User first name (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.length() >= 2 && input.length() <= 20) {
				u.setFirstName(input);
			} else {
				System.err.println("First name must be between 2 and 20 characters long. (ex. John)");
			}
		} while(u.getFirstName() == null);
		
		do {
			System.out.println("User last name (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.length() >= 2 && input.length() <= 20) {
				u.setLastName(input);
			} else {
				System.err.println("Last name must be between 2 and 20 characters long. (ex. Smith)");
			}
		} while(u.getLastName() == null);
		
		do {
			System.out.println("E-mail (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.matches("^(.+)@(.+)$")) {
				u.setEmail(input);
			} else {
				System.err.println("E-mail must be valid. (ex. john@gmail.com");
			}
		} while(u.getEmail() == null);
		
		do {
			System.out.println("User password (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.length() >= 5 && input.length() <= 30) {
				u.setPassword(input);
			} else {
				System.err.println("Password must be between 5 and 30 characters long.");
			}
		} while(u.getPassword() == null);
		
		do {
			StringBuilder sb = new StringBuilder("User role (empty line for cancel) ["); 	
			for(int i = 0; i < Role.ALL_ROLES.length; i++) {
				if(i > 0) {
					sb.append(", ");
				}
				sb.append(i).append(":").append(Role.ALL_ROLES[i].getName());
			}
			sb.append("]:");
			System.out.println(sb); // print choices
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			try {
				int choice = Integer.parseInt(input);
				if(choice >= 0 && choice < Role.ALL_ROLES.length ) {
					Set<Role> roles  = new HashSet<>();
					roles.add(Role.ALL_ROLES[choice]);
					u.setRoles(roles);
				} else {
					System.err.printf("The choice should be between %d and %d\n", 0, Role.ALL_ROLES.length - 1);
				}
			}catch(NumberFormatException ex) {
				System.err.println("Invalid number. Try again.");
			}
		} while(u.getRoles().size() == 0);
		
		return u;
	}
	
	
	public static int paseInt(String input, int min, int max) {
		try {
			int choice = Integer.parseInt(input);
			if(choice >= min && choice <= max ) {
				return choice;
			} else {
				return -1;
			}
		}catch(NumberFormatException ex) {
			return -1;
		}
	}
}
