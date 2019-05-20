package invoicing.util;

import java.util.Comparator;

import invoicing.model.Contragent;

public class ContragentComparatorByName implements Comparator<Contragent> {

	@Override
	public int compare(Contragent o1, Contragent o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

}
