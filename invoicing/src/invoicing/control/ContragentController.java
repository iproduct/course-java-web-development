package invoicing.control;

import java.util.ArrayList;
import java.util.List;

import invoicing.exceptions.EntityExistsException;
import invoicing.model.Contragent;
import invoicing.model.Customer;
import invoicing.model.Supplier;

public class ContragentController {
	private List<Contragent> contragents = new ArrayList<>();
	
	public void addContragent(Contragent c) throws EntityExistsException {
		if(contragents.contains(c)) {
			throw new EntityExistsException(
				String.format("Entity ID=%s already exists.", c.getIdNumber()));
		}
		contragents.add(c);
	}
	
	public String getContragentsReport() {
		StringBuilder sb = new StringBuilder();
		for(Contragent c: contragents) {
			sb.append(c.toString()).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws EntityExistsException {
		ContragentController ctrl = new ContragentController();
		ctrl.addContragent(
				new Supplier("123456789", "ABC Ltd.", "Sofia 1000", "RBBABZ1234566778878", "RBBABZ123", "Ivan Petrov"));
		ctrl.addContragent(
				new Customer("82122423412", "Dimitar Jekov", "Plovdiv, Ciclama 15"));
		System.out.println(ctrl.getContragentsReport());
	}
	
}
