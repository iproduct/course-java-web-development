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
import users.model.ActionType;
import users.model.Resource;

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
			new Resource("Products endpoint", 1, "/api/products", REST_ENDPOINT, restResourceActions),
			new Resource("Best rated products endpoint", 2, "/api/products/rated", REST_ENDPOINT, restResourceActions),
			new Resource("Most popular products endpoint", 4, "/api/products/popular", REST_ENDPOINT, restResourceActions),
			new Resource("App config file", 1, "/~/api/config/application.properties", FILE, resourceFileActions),
			new Resource("Configuration directory", 1, "/~/api/config", DIRECTORY, resourceDirectoryActions)
	};
	
	@Mock
	private ResourceRepository resourceRepo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	// Tests

	@Test
	public void testGetAllResources() {
		Mockito.when(resourceRepo.findAll()).thenReturn(Arrays.asList(resources));
		ResourceService service = new ResourceServiceImpl(resourceRepo);
		System.out.println(service.getAllResources());
		assertEquals(Arrays.asList(resources), service.getAllResources());
	}

	@Test
	public void testGetResourceById() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateResource() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateResource() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteResourceById() {
		fail("Not yet implemented");
	}

}
