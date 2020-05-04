package users.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import users.UsersSimpleDemo;
import users.dao.ResourceRepository;
import users.dao.UserRepository;
import users.dao.factory.AbstractRepoFactory;
import users.dao.factory.RepositoryFactoryProvider;
import users.dao.factory.RepositoryType;
import users.services.LoginService;
import users.services.LoginServiceImpl;
import users.services.ResourceService;
import users.services.ResourceServiceImpl;
import users.services.UserService;
import users.services.UserServiceImpl;

// Problem 4.III
public class ApplicationController {
	private static Logger logger = Logger.getLogger(ApplicationController.class.getSimpleName());
	private UserService userService;
	private ResourceService resourceService;
	private LoginService loginService;
	
	private static class SingletonHelper {
		private static final ApplicationController INSTANCE = new ApplicationController();
	}

	public static ApplicationController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public static ResourceService createResourceService(ResourceRepository resourceRepo) {
		return new ResourceServiceImpl(resourceRepo);
	};
	
	public static UserService createUserService(UserRepository userRepo, ResourceService resourceService) {
		return new UserServiceImpl(userRepo, resourceService);
	};
	
	public static LoginService createLoginService(UserService userService) {
		return new LoginServiceImpl(userService);
	};
	
	private ApplicationController(){
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
		
		// create repositories
		AbstractRepoFactory repoFactory = RepositoryFactoryProvider.getRepositoryFactory(RepositoryType.XML_FILE, properties);
		ResourceRepository resourceRepo = repoFactory.getResourceRepository();
		UserRepository userRepo = repoFactory.getUserRepository();
		
		// create singleton services
		resourceService = createResourceService(resourceRepo);
		userService = createUserService(userRepo, resourceService);
		loginService = createLoginService(userService);
	}
	
	public UserService getUserService() {
		
		return userService;
	}
	
	public ResourceService getResourceService() {
		return resourceService;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}
}
