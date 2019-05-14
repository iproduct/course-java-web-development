package grouper.task;

import static grouper.utils.Operation.ADD;
import static grouper.utils.Operation.DELETE;
import static grouper.utils.Operation.EDIT;
import static grouper.utils.Operation.GET_50;
import static grouper.utils.Operation.GET_ONE;
import grouper.controller.VehicleController;
import grouper.model.Vehicle;
import grouper.utils.Operation;
import grouper.utils.VehicleUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class VehicleTask implements Callable<TaskStatistics> {
	private String id;
	private VehicleController controller;
	private long duration, currentDurationMs, getOperations, bulkGetOperations, recordsAdded, 
		recordsDeleted, recordsUpdated, startTime, endTime, iterations;
	private Random rand = new Random();
	
	
	public VehicleTask(String taskId, VehicleController vc, long durationMs) {
		id = taskId;
		controller = vc;
		duration = durationMs;
	}

	@Override
	public TaskStatistics call() throws Exception {
		startTime = System.nanoTime();
		List<Vehicle> results;
		long vehicleSeqNumber;
		do {
			iterations++;
			int r = rand.nextInt(1000);
			Operation op;
			if (r < 5) {
				op = ADD;
			} else if (r < 6) {
				op = EDIT;
			} else if (r < 8) {
				op = DELETE;
			} else if (r < 100) {
				op = GET_50;
			} else {
				op = GET_ONE;
			} 
			
			switch (op) {
				case ADD : controller.add(VehicleUtils.makeRandomVehicle()); 
					recordsAdded++; 
					break;
				case EDIT : vehicleSeqNumber = VehicleUtils.getRandomLongNumber(controller.size()); 
					results = controller.get(vehicleSeqNumber,1);
					if(results.size() > 0){
						Vehicle v = results.get(0);
						Vehicle newVehicle = VehicleUtils.makeRandomVehicleOfType(v.getType());
						v.setPlaces(newVehicle.getPlaces());
						v.setPrice(newVehicle.getPrice());
						v.setGpsNumber(newVehicle.getGpsNumber());
					}
					recordsUpdated++;
					break;
				case DELETE : vehicleSeqNumber = VehicleUtils.getRandomLongNumber(controller.size()); 
					results = controller.get(vehicleSeqNumber,1);
					if(results.size() > 0){
						Vehicle v = results.get(0);
						controller.remove(v.getId());
					}
					recordsDeleted++;
					break;
				case GET_50 : vehicleSeqNumber = VehicleUtils.getRandomLongNumber(controller.size()); 
					controller.get(vehicleSeqNumber,50); 
					bulkGetOperations++; 
					break;
				default: vehicleSeqNumber = VehicleUtils.getRandomLongNumber(controller.size()); 
					controller.getById(vehicleSeqNumber); 
					getOperations++; 
					break;
			}
			
			endTime = System.nanoTime();
			currentDurationMs = (endTime - startTime)/1000000;
		} while (currentDurationMs < duration);
		
		return new TaskStatistics(id, recordsAdded, recordsDeleted, recordsUpdated, getOperations, bulkGetOperations, 
			(100000 * (recordsAdded + recordsDeleted + recordsUpdated + getOperations + bulkGetOperations) / currentDurationMs)
			/ 100D); //two digits after decimal point accuracy
	}

}
