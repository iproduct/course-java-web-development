package invoicing.util;

import java.util.Comparator;

import invoicing.model.Contragent;

public class ContragentComparatorById implements Comparator<Contragent> {

	@Override
	public int compare(Contragent o1, Contragent o2) {
		return Long.compare(o1.getIdNumber(), o2.getIdNumber());
	}

}
