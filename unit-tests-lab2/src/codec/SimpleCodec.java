package codec;

public class SimpleCodec {
	protected int key;
	
	public SimpleCodec(int key) {
		this.key = key;
	}

	public String encode(String input) {
		StringBuilder sb = new StringBuilder(input);
		int len = input.length();
		for(int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			ch = (char)(ch + key);
			sb.setCharAt((i + key) % len, ch);
		}
		return sb.toString();
	}
	
	public String decode(String encoded) {
		StringBuilder sb = new StringBuilder(encoded);
		int len = encoded.length();
		for(int i = 0; i < len; i++) {
			char ch = encoded.charAt(i);
			ch = (char)(ch - key);
			sb.setCharAt((i - key % len + len) % len, ch);
		}
		return sb.toString();
	}
}
