package grouper.utils;

import grouper.model.Vehicle;

public class VehicleUtils {

	public static Vehicle makeRandomVehicle() {
		return makeRandomVehicleOfType(getRandomVehicleType());
	}
	
	public static Vehicle makeRandomVehicleOfType(VehicleType type) {
		Vehicle v = new Vehicle();
		v.setType(type);
		v.setNumber(makeRandomNumber());
		v.setPlaces(getRandomNumber(v.getType().getMaxPlaces() - v.getType().getMinPlaces()) 
				+ v.getType().getMinPlaces());
		v.setPrice(((int)(v.getPlaces() * 0.2 * Math.log(v.getPlaces()* (Math.random()+1))*100))/100D);
		v.setGpsNumber(getRandomLongNumber(10000000000L));
		return v;
	}
	
	public static String makeRandomNumber() {
		StringBuilder plate = new StringBuilder();
		plate.append(getRandomLetter()).append(getRandomLetter()) //two letters
			.append(getRandomDigit()).append(getRandomDigit()) 
			.append(getRandomDigit()).append(getRandomDigit()) // four digits
			.append(getRandomLetter()).append(getRandomLetter()); //two letters
		return plate.toString();
	}
	
	public static char getRandomLetter(){
		return (char)('A' + Math.random() * 26);
	}

	public static int getRandomDigit(){
		return (int)(Math.random() * 10);
	}

	public static int getRandomNumber(int range){
		return (int)(Math.random() * range);
	}
	
	public static long getRandomLongNumber(long range){
		return (long)(Math.random() * range);
	}
	
	public static String getRandomNumberString(int numDigits){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numDigits; i++) {
			sb.append(getRandomNumber(10));
		}
		return sb.toString();
	}
	
	public static VehicleType getRandomVehicleType() {
		VehicleType[] vals = VehicleType.values();
		return vals[getRandomNumber(vals.length)];
	}

}
