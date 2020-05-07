package users.services;

import static org.junit.Assert.*;
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
import org.mockito.Spy;

import users.dao.ResourceRepository;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.exceptions.PropertyChangeNotAllowedException;
import users.model.ActionType;
import users.model.Permission;
import users.model.Resource;
import users.model.Role;
import users.model.User;

public class ResourceServiceImplTest {
	static final Set<ActionType> restResourceActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
			CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, UPDATE_OWN, READ_OWN, DELETE_OWN
	}));
	static final Set<ActionType> resourceFileActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
			CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, WRITE_ALL, UPDATE_OWN, READ_OWN, WRITE_OWN, DELETE_OWN, EXECUTE
	}));
	static final Set<ActionType> resourceDirectoryActions = new HashSet<ActionType>(Arrays.asList(new ActionType[] {
			CREATE, UPDATE_ALL, READ_ALL, DELETE_ALL, UPDATE_OWN, READ_OWN, DELETE_OWN, WRITE_ALL, WRITE_OWN, 
	}));

	static final Resource[] resources = {
			new Resource(1L, "Products endpoint", 1, "/api/products", REST_ENDPOINT, restResourceActions),
			new Resource(2L, "Best rated products endpoint", 2, "/api/products/rated", REST_ENDPOINT, restResourceActions),
			new Resource(3L, "Most popular products endpoint", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions),
			new Resource(4L, "App config file", 1, "/~/api/config/application.properties", FILE, resourceFileActions),
			new Resource(5L, "Configuration directory", 1, "/~/api/config", DIRECTORY, resourceDirectoryActions)
	};
	
	static final Resource newResource = 
			new Resource("Most popular products endpoint", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions);
			
	static final Resource invalidResource = 
			new Resource("", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions);
	
	static final Resource updatedResource = 
			new Resource(3L, "New products endpoint", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions);
			
	static final Resource invalidResourceUpdate = 
			new Resource(3L, "", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions);
			

			
	@Mock
	private ResourceRepository resourceRepo;
	
	private ResourceServiceImpl service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new ResourceServiceImpl(resourceRepo);
	}

	
	// Tests

	@Test
	public void getAllResources_ReturnResources_ResourcesExist() {
		Mockito.when(resourceRepo.findAll()).thenReturn(Arrays.asList(resources));
//		System.out.println(service.getAllResources());
		assertEquals(Arrays.asList(resources), service.getAllResources());
	}

	@Test
	public void getResourceById_ReturnResourceWithId_ResourceExists() throws NonexistingEntityException {
		final long testId = 4L;
		Mockito.when(resourceRepo.findById(testId)).thenReturn(resources[3]);
//		System.out.println(service.getResourceById(testId));
		assertEquals(resources[3], service.getResourceById(testId));
	}

	@Test(expected = NonexistingEntityException.class)
	public void getResourceById_throwNonexistingEntityException_ResourceDoesNotExist() throws NonexistingEntityException {
		final long testId = 7L;
		Mockito.when(resourceRepo.findById(testId)).thenReturn(null);
		service.getResourceById(testId);
	}

	@Test
	public void createResource_ReturnCreatedResource_ResourceDoesNotExist() throws InvalidEntityDataException {
		Mockito.when(resourceRepo.create(newResource)).thenReturn(resources[2]);
		assertEquals(resources[2], service.createResource(newResource));
	}

	@Test(expected = InvalidEntityDataException.class)
	public void createResource_throwInvalidEntityDataException_ResourceDataInvalid() throws InvalidEntityDataException {
		Mockito.when(resourceRepo.create(invalidResource)).thenThrow(new InvalidEntityDataException());
		service.createResource(invalidResource);
	}

	@Test
	public void updateResource() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		Mockito.when(resourceRepo.update(updatedResource)).thenReturn(updatedResource);
		assertEquals(updatedResource, service.updateResource(updatedResource));
	}
	
	@Test(expected = NonexistingEntityException.class)
	public void updateResource_throwInvalidEntityDataException_ResourceDataInvalid() throws NonexistingEntityException, InvalidEntityDataException, PropertyChangeNotAllowedException {
		Mockito.when(resourceRepo.update(invalidResourceUpdate)).thenThrow(new NonexistingEntityException());
		service.updateResource(invalidResourceUpdate);
	}


	@Test
	public void deleteResourceById_ReturnResourceWithId_ResourceExists() throws NonexistingEntityException {
		final long testId = 4L;
		Mockito.when(resourceRepo.removeById(testId)).thenReturn(resources[3]);
		assertEquals(resources[3], service.deleteResourceById(testId));
	}

	@Test(expected = NonexistingEntityException.class)
	public void deleteResourceById_throwNonexistingEntityException_ResourceDoesNotExist() throws NonexistingEntityException {
		final long testId = 7L;
		Mockito.when(resourceRepo.removeById(testId)).thenReturn(null);
		service.getResourceById(testId);
	}

}
