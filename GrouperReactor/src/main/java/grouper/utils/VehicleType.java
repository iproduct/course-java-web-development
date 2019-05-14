package grouper.utils;

public enum VehicleType {
	COMPACT(3, 3), NORMAL(3,4), 
	LIMUSINE(4,6), VAN(6,11), BUS(10,50);
	
	private int minPlaces, maxPlaces;
	
	private VehicleType(int minNumPlaces, int maxNumPlaces) {
		this.minPlaces = minNumPlaces;
		this.maxPlaces = maxNumPlaces;
	}
	
	public int getMinPlaces() {
		return minPlaces;
	}
	
	public int getMaxPlaces() {
		return maxPlaces;
	}
}
