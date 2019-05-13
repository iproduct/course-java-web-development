package fundamentals;

public class StackAndHeap {
	int n = 42;
	boolean flag = true;
	char ch = 'X';
	Integer wrapper = n;
	String message = "Hi from Java!";
	int[] fibonacci = { 0, 1, 1, 2, 3, 5, 8 };
	
	void changeValues() {
		n = 100;
		flag = !flag;
		ch = ++ch;
		wrapper = ++wrapper;
		message = null;
		fibonacci[2] = 42;
	}
	
	public static void main(String[] args) {
		new StackAndHeap().changeValues();
	}
}
