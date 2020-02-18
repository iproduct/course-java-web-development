package strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleCoderTest {

	@Test
	public void testEncode() {
		SimpleCoder sc = new SimpleCoder();
		String encoded;
		encoded = sc.encode("abcd", 1);
		assertNotNull(encoded);
		assertEquals("ebcd", encoded);
	}

	@Test
	public void testEncodeDecode() {
		SimpleCoder sc = new SimpleCoder();
		String encoded, decoded;
		encoded = sc.encode("I love Java!", 15);
		assertNotNull(encoded);
		decoded = sc.decode(encoded, 15);
		assertNotNull(decoded);
		assertEquals("I love Java!", decoded);
	}

}
