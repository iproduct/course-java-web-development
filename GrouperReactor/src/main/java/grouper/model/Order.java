package grouper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String fromAddress;

	private double fromLat;

	private double fromLon;

	private Date pickupTime;

	private String toAddress;


	private double toLat;

	private double toLon;

	private int vechicleType;

	private Customer customer;

	private List<Payment> payments;

	public Order() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public double getFromLat() {
		return this.fromLat;
	}

	public void setFromLat(double fromLat) {
		this.fromLat = fromLat;
	}

	public double getFromLon() {
		return this.fromLon;
	}

	public void setFromLon(double fromLon) {
		this.fromLon = fromLon;
	}

	public Date getPickupTime() {
		return this.pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getToAddress() {
		return this.toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public double getToLat() {
		return this.toLat;
	}

	public void setToLat(double toLat) {
		this.toLat = toLat;
	}

	public double getToLon() {
		return this.toLon;
	}

	public void setToLon(double toLon) {
		this.toLon = toLon;
	}

	public int getVechicleType() {
		return this.vechicleType;
	}

	public void setVechicleType(int vechicleType) {
		this.vechicleType = vechicleType;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setOrder(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setOrder(null);

		return payment;
	}

}