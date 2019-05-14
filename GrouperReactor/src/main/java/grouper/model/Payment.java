package grouper.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the payment database table.
 * 
 */
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;


	private long id;

	private double amount;

	private String cardNumber;

	private Date cardValidDate;

	private int status;

	private Order order;

	public Payment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getCardValidDate() {
		return this.cardValidDate;
	}

	public void setCardValidDate(Date cardValidDate) {
		this.cardValidDate = cardValidDate;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}