package grouper.model;

import java.io.Serializable;


/**
 * The persistent class for the driver database table.
 * 
 */
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String address;

	private String city;

	private String name;

	private String phone;

	private long vechicleId;

	private Vehicle vehicle;

	public Driver() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getVechicleId() {
		return this.vechicleId;
	}

	public void setVechicleId(long vechicleId) {
		this.vechicleId = vechicleId;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}