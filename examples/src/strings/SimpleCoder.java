package strings;

public class SimpleCoder {

	public String encode(String message, int key) {
		StringBuilder sb = new StringBuilder(message), result = new StringBuilder(
				message);
		int len = message.length();

		// change char code
		for (int i = 0; i < len; i++)
			sb.setCharAt(i, (char) (sb.charAt(i) + key));

		// shift chars
		for (int i = 0; i < len; i++) {
			result.setCharAt((i + key) % len, sb.charAt(i));
		}

		return result.toString();
	}

	public String decode(String encodedMessage, int key) {
		StringBuilder result = new StringBuilder(encodedMessage);
		int len = encodedMessage.length();

		// change char code & shift chars
		for (int i = 0; i < len; i++) {
			int r = (i - key) % len;
			result.setCharAt((r >= 0)? r : r + len,
					(char) (encodedMessage.charAt(i) - key));
		}

		return result.toString();
	}

	public static void main(String[] args) {
		SimpleCoder sc = new SimpleCoder();
		String encoded;
		System.out.println(encoded = sc.encode("I love Java 8", 15));
		System.out.println(sc.decode(encoded, 15));

	}

}
