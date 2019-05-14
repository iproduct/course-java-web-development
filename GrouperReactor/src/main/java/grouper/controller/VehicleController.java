package grouper.controller;

import grouper.controller.impl.inmemory.EntityExistsException;
import grouper.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleController {
	void add(Vehicle vehicle) throws EntityExistsException;
	void edit(Vehicle vehicle) throws EntityExistsException;
	void remove(long vehicleId) throws EntityExistsException;
	Optional<Vehicle> getById(long vehicleId);
	Optional<Vehicle> getByPlateNumber(String plateNumber);
	List<Vehicle> get(long from, int maxCount);
	long size();
}
