package org.iproduct.eshop.parser;

import java.io.InputStream;

import org.iproduct.eshop.entity.SoftwareItem;

public class ExtendedParser extends Parser {

	public ExtendedParser(InputStream in) {
		super(in);
	}

	@Override
	public SoftwareItem inputItemFactory() {
		SoftwareItem sit = new SoftwareItem();
		super.inputItem(sit);
		//input description
		System.out.print("Software Type: ");
		sit.setSoftwareType(sc.nextLine().trim());
		
		//input description
		System.out.print("Version: ");
		sit.setVersion(sc.nextLine().trim());
		
		//input description
		System.out.print("Platform: ");
		sit.setPlatform(sc.nextLine().trim());

		return  sit;
	}
	
		public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
