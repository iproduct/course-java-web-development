package fundamentals;

public class VarScopes {
	static int s1 = 25;
	int i1 = 350;

	public static void main(String[] args) {
		if(s1 > 10){
			int a = 42;
			// Only a available
			{
				int b = 108;
				// Both a & b are available
			}
			// Only a available
			// b is out of scope
		}
		// a & b are out of scope
	}
}
