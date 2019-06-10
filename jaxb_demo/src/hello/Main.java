package hello;

import javax.swing.JButton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {

	private ObjectFactory of;
	private GreetingListType grList;
	
	

	public Main() {
		of = new ObjectFactory();
		grList = of.createGreetingListType();
	}

	public void make(String message, String lang){
		GreetingType g = of.createGreetingType();
		g.setText(message);
		g.setLanguage(lang);
		grList.getGreeting().add(g);
	}

	public void marshal(){
		JAXBElement<GreetingListType> gl = 
			of.createGreetings(grList);
		try {
			JAXBContext jc = JAXBContext.newInstance("hello");
			Marshaller m = jc.createMarshaller();
			m.marshal(gl, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Main m = new Main();
		m.make("Hello from JAXB!", "en");
		m.make("Bonjour JAXB!", "fr");
		m.marshal();

	}

}
