package codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import codec.exception.InvalidDataException;

@RunWith(Parameterized.class)
public class SimpleCodecTestParams {

	@Parameters(name = "[{index}]. Key:{0}, text:{1}, encoded text:{2}")
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[][] {
            {3, "abcd", "efgd"},
            {2, "abcd", "efcd"},
            {5, "a", "f"},
       });
    }

	private int key;
	private String text;
	private String encodedText;
	
	public SimpleCodecTestParams(int key, String text, String encodedText) {
		this.key = key;
		this.text = text;
		this.encodedText = encodedText;
	}

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
		codec = new SimpleCodec(key);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("    ---- After test case.");
		codec = null;
	}

	@Test
	public void testEncode() throws InvalidDataException {
		System.out.println("           ---- testEncode()");
		// 1. Setup - preconditions
//		SimpleCodec codec = new SimpleCodec(KEY);
		
		// 2. Call the function - action
		String result = codec.encode(text);
		
		// 3. Assert that the actual result == expected result - postondition
		assertNotNull("Result should not be null.", result);
		assertEquals("Result not encoded correctly.", encodedText, result);
	}

	@Test
	public void testDecode() throws InvalidDataException {
		System.out.println("           ---- testDecode()");

		// 1. Setup - preconditions - in setUp() method
		
		// 2. Call the function - action
		String result = codec.decode(encodedText);
		
		// 3. Assert that the actual result == expected result - postondition
		assertNotNull("Result should not be null.", result);
		assertEquals("Result not decoded correctly.", text, result);
	}

}
