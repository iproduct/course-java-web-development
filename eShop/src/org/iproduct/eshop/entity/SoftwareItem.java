package org.iproduct.eshop.entity;

public class SoftwareItem extends Item {
	private String softwareType = "Business Software";
	private String version;
	private String platform;

	
	public SoftwareItem() {
	}


	public SoftwareItem(long id, String name, String manufacturer,
			String category, String description, double price, int stockQuantity) {
		super(id, name, manufacturer, category, description, price, stockQuantity);
	}


	public SoftwareItem(long id, String name, String manufacturer) {
		super(id, name, manufacturer);
	}

	public SoftwareItem(long id, String name, String manufacturer,
			String category, String description, double price,
			int stockQuantity, String softwareType, String version,
			String platform) {
		super(id, name, manufacturer, category, description, price,
				stockQuantity);
		this.softwareType = softwareType;
		this.version = version;
		this.platform = platform;
	}


	/**
	 * @return the softwareType
	 */
	public String getSoftwareType() {
		return softwareType;
	}


	/**
	 * @param softwareType the softwareType to set
	 */
	public void setSoftwareType(String softwareType) {
		this.softwareType = softwareType;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}


	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}


	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SoftwareItem [id=").append(getId())
				.append(", name=").append(getName())
				.append(", nanufacturer=").append(getManufacturer())
				.append(", category=").append(getCategory())
				.append(", description=").append(getDescription())
				.append(", price=").append(getPrice())
				.append(", stockQuantity=").append(getStockQuantity())
				.append("softwareType=").append(softwareType)
				.append(", version=").append(version).append(", platform=")
				.append(platform).append("]");
		return builder.toString();
	}


	public static void main(String[] args) {
		SoftwareItem si1 = new SoftwareItem(1, "Windows 8", "Microsoft", 
				"Software", "Last Windows OEM version", 150, 12, "OS", "8.1", "PC");
		System.out.println(si1);

	}

}
