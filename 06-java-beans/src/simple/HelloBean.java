package simple;

class PropertyType {
}
class XxxListener {
}

public class HelloBean {
	private String congratMessage = "Hello";
	private PropertyType propertyName;                // declaration
	public PropertyType getPropertyName() {/*...*/ return null; }     // getter
	public void setPropertyName(PropertyType p) {/*...*/} // setter
	
	
	public String getCongratMessage() {
		return congratMessage;
	}
	public void setCongratMessage(String congratMessage) {
		this.congratMessage = congratMessage;
	}
	public void addXxxListener(XxxListener l) {}
	public void removeXxxListener(XxxListener l) {}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
