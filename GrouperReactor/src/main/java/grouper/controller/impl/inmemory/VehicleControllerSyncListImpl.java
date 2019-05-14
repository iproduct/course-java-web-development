package grouper.controller.impl.inmemory;

import grouper.controller.VehicleController;
import grouper.model.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleControllerSyncListImpl implements VehicleController {
	private final Map<Long, Vehicle> vehicles;
	private static long count = 0;
	
	synchronized protected long getNextId() {
		return count++;
	}
	
	public VehicleControllerSyncListImpl(int size) {
		vehicles = Collections.synchronizedMap(new HashMap<>(size));
	}

	@Override
	public void add(Vehicle vehicle) throws EntityExistsException {
		if (getByPlateNumber(vehicle.getNumber()).isPresent())
			throw new EntityExistsException("The vehicle with plate number " + vehicle.getNumber() + " already exists in DB." );
		vehicle.setId(getNextId());
		vehicles.put(vehicle.getId(), vehicle);
	}

	@Override
	public void edit(Vehicle vehicle) throws EntityExistsException {
		if (getById(vehicle.getId()) == null)
			throw new EntityExistsException("The vehicle with ID " + vehicle.getId() + " does not exist in DB." );
		vehicles.put(vehicle.getId(), vehicle);
	}

	@Override
	public void remove(long vehicleId) throws EntityExistsException {
		if (getById(vehicleId) == null)
			throw new EntityExistsException("The vehicle with ID " + vehicleId + " does not exist in DB." );
		vehicles.remove(vehicleId);
	}

	@Override
	public Optional<Vehicle> getById(long vehicleId) {
		return Optional.ofNullable(vehicles.get(vehicleId));
	}

	@Override
	public Optional<Vehicle> getByPlateNumber(String plateNumber) {
		synchronized(vehicles){
			return vehicles.values().stream()
					.filter(vehicle -> vehicle.getNumber().equalsIgnoreCase(plateNumber))
					.findAny();
		}
	}

	@Override
	public List<Vehicle> get(long fromCount, int maxCount) {
		Collection<Vehicle> vals = vehicles.values();
		List<Vehicle> results = new ArrayList<Vehicle>();
//		long count = 0;
		
		synchronized (vehicles) {  // Synchronizing on m, not s!
			results = vehicles.values().parallelStream().sorted().skip(fromCount).limit(maxCount)
					.collect(Collectors.toList());
		}
//	      Iterator i = s.iterator(); // Must be in synchronized block
//	      while (i.hasNext())
//	          foo(i.next());
//	  }
//		
//		
//		for(Vehicle val: vals){
//			if(count >= fromCount)
//				results.add(val);
//			if(count >=  fromCount + maxCount) 
//				break;
//			count++;
//		}
		return results;
//		return vehicles.values().parallelStream().sorted().skip(fromCount).limit(maxCount).collect(Collectors.toList());
	}

	@Override
	public long size() {
		return vehicles.size();
	}

}
