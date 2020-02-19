package codec;

import codec.exception.InvalidDataException;

public class SimpleCodec {
	public static int MAX_CHAR_CODE = 1024;
	
	protected int key;
	
	public SimpleCodec(int key) {
		this.key = key;
	}

	public String encode(String input) throws InvalidDataException {
		StringBuilder sb = new StringBuilder(input);
		int len = input.length();
		for(int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			if(ch + key > MAX_CHAR_CODE) {
				throw new InvalidDataException("Encoded character code: " + (int) ch + " > " + MAX_CHAR_CODE + ".");
			}
			ch = (char)(ch + key);
			sb.setCharAt((i + key) % len, ch);
		}
		return sb.toString();
	}
	
	public String decode(String encoded) throws InvalidDataException {
		StringBuilder sb = new StringBuilder(encoded);
		int len = encoded.length();
		for(int i = 0; i < len; i++) {
			char ch = encoded.charAt(i);
			if(ch - key <= 0) {
				throw new InvalidDataException("Decoded character code should be positive, but found: " + (int) ch + ".");
			}
			ch = (char)(ch - key);
			sb.setCharAt((i - key % len + len) % len, ch);
		}
		return sb.toString();
	}
}
