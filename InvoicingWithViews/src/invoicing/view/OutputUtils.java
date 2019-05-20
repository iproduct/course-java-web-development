package invoicing.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import invoicing.controller.InvoiceRegister;
import invoicing.model.Invoice;
import invoicing.model.Position;

public class OutputUtils {

	/**
	 * This method prints the invoice as text.
	 * @param inv invoice to be printed
	 * @return formatted text layout of the inoce
	 */
	public static String formatInvoice(Invoice inv){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append("\nINVOICE")
			.append("\n==========")
			.append("\nNo. : ").append(String.format("%010d  ", inv.getNumber()))
			.append("\nDate: ").append(sdf.format(inv.getDate()))
			.append("\n\nISSUER: \n").append(inv.getIssuer().format())
			.append("\n\nRECEIVER: \n").append(inv.getReceiver().format());

		builder.append(
				String.format("\n\n| %3s | %-30.30s | %8s | %8s | %7s | %10s |", 
					"No.", "Product", "Price", "Quantity", "Measure", "Total")
			);

		int n = 1;
		for(Position pos : inv.getPositions()){
			builder.append(
				String.format("\n| %3d | %-30.30s | %8.2f | %8.2f | %7s | %10.2f |",
					n++, pos.getProduct().getName(), 
					pos.getPrice(), pos.getQuantity(), "PCS",
					pos.getTotalPrice())
			);
		}
		
		builder.append(String.format("\n\n%66sTotal: %10.2f", "", inv.getTotal()))
		.append(String.format("\n%68sVAT: %10.2f", "", inv.getVAT()))
		.append(String.format("\n%84s", "-----------------"))
		.append(String.format("\n\n%53sTotal VAT included: %10.2f", "", inv.getTotalPlusVAT()));
			
			
		return builder.toString();
	}

	
	public static String getInvoicesReportForCurrentIssuer(InvoiceRegister register){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append("\nR E P O R T")
			.append("\n==============")
			.append("\nDate: ").append(sdf.format(new Date()))
			.append("\n\nINVOICES ISSUER:")
			.append("\nID Number: ").append(register.getCurrentIssuer().getIdNumber())
			.append("\nName: ").append(register.getCurrentIssuer().getName());

		builder.append(
				String.format("\n\n| %3s | %10s | %12s | %-25.25s | %10s | %9s | %10s |", 
					"No.", "Date   ", "Receiver ID", "Receiver", "Amount", "VAT", "Total")
			);

		int n = 0;
		double total = 0;
		for(Invoice inv : register.getInvoicesForCurrentIssuer()){
			total += inv.getTotal();
			builder.append(
					String.format("\n| %3d | %10s | %12d | %-25.25s | %10.2f | %9.2f | %10.2f |", 
					++n, sdf.format(inv.getDate()), 
					inv.getReceiver().getIdNumber(), inv.getReceiver().getName(),
					inv.getTotal(), inv.getVAT(), inv.getTotalPlusVAT())
			);
		}
		
		builder.append(String.format("\n\n%76sTotal amount: %10.2f", "", total))
		.append(String.format("\n%79sTotal VAT: %10.2f", "", total * Position.VAT_RATE))
		.append(String.format("\n%95s", "---------------------------"))
		.append(String.format("\n\n%70sTotal VAT included: %10.2f", "", total * (1 + Position.VAT_RATE)));
			
		return builder.toString();
	}

	public static String getInvoicesReportForCurrentIssuerCSV(InvoiceRegister register){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append("R E P O R T")
			.append("\nDate:,").append(sdf.format(new Date()))
			.append("\nINVOICES ISSUER:")
			.append("\nID Number:,").append(register.getCurrentIssuer().getIdNumber())
			.append("\nName:,").append(register.getCurrentIssuer().getName());

		builder.append(
				String.format("\n\n%s,%s,%s,%s,%s,%s,%s", 
					"No.", "Date", "Receiver ID", "Receiver", "Amount", "VAT", "Total")
			);

		int n = 0;
		double total = 0;
		for(Invoice inv : register.getInvoicesForCurrentIssuer()){
			total += inv.getTotal();
			builder.append(
					String.format("\n%d,%s,%d,%s,%10.2f,%9.2f,%10.2f", 
					++n, sdf.format(inv.getDate()), 
					inv.getReceiver().getIdNumber(), inv.getReceiver().getName(),
					inv.getTotal(), inv.getVAT(), inv.getTotalPlusVAT())
			);
		}
		
		builder.append(String.format("\n\nTotal amount:,%10.2f", total))
		.append(String.format("\nTotal VAT:,%10.2f", total * Position.VAT_RATE))
		.append(String.format("\nTotal VAT included:,%10.2f", total * (1 + Position.VAT_RATE)));
			
		return builder.toString();
	}



}
