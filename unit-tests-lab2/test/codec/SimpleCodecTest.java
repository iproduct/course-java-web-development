package codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SimpleCodecTest {
	public static final int KEY = 3;
	public static final String SAMPLE_TEXT = "abcd";
	public static final String ENCODED_TEXT = "efgd";
	
	private SimpleCodec codec;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("---- Before class.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("---- After class.");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("    ---- Before test case.");
		codec = new SimpleCodec(KEY);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("    ---- After test case.");
		codec = null;
	}

	@Test
	public void testEncode() {
		System.out.println("           ---- testEncode()");
		// 1. Setup - preconditions
//		SimpleCodec codec = new SimpleCodec(KEY);
		
		// 2. Call the function - action
		String result = codec.encode(SAMPLE_TEXT);
		
		// 3. Assert that the actual result == expected result - postondition
		assertNotNull("Result should not be null.", result);
		assertEquals("Result not encoded correctly.", ENCODED_TEXT, result);
	}

	@Test
	public void testDecode() {
		System.out.println("           ---- testDecode()");

		// 1. Setup - preconditions - in setUp() method
		
		// 2. Call the function - action
		String result = codec.decode(ENCODED_TEXT);
		
		// 3. Assert that the actual result == expected result - postondition
		assertNotNull("Result should not be null.", result);
		assertEquals("Result not decoded correctly.", SAMPLE_TEXT, result);
	}

}
