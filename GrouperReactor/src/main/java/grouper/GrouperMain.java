package grouper;

import static grouper.utils.VehicleUtils.makeRandomVehicle;
import grouper.controller.VehicleController;
import grouper.controller.impl.inmemory.VehicleControllerConcurentHashMapImpl;
import grouper.task.TaskStatistics;
import grouper.task.VehicleTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GrouperMain {
	public final static int NUM_THREADS = Runtime.getRuntime().availableProcessors();
	public final static int INITIAL_DB_SIZE = 20000; //initially we start with 500000 Db records
	public final static long WARMUP_DURATION_MS = 180000; //60 seconds
	public final static long BENCHMARK_DURATION_MS = 180000; //60 seconds
	public final static long WARMING_ITERATIONS = 100; 
	static long  recordsAdded = 0, recordsDeleted = 0, recordsUpdated = 0, 
			getOperations = 0, bulkGetOperations = 0; 
	static double operationsPerSecond = 0;

	public static void main(String[] args) {
		VehicleController controller = 
				new  VehicleControllerConcurentHashMapImpl(2*INITIAL_DB_SIZE);
		System.out.println("Filling DB with " + INITIAL_DB_SIZE + " records ...");
		long  start = System.nanoTime();
		fillInitialRecords(controller, INITIAL_DB_SIZE);
		long end = System.nanoTime();
		System.out.println("!!!!!!!!!!!!!! Initialization took: " + (end-start)/1000000 + " ms");
		
		//Create ThreadPool
		ExecutorService exec = Executors.newFixedThreadPool(NUM_THREADS);

		//Warm up
		System.out.println("\n\n\n!!!!!!!!!!!!!!!!!  WARMING UP !!!!!!!!!!!!!!!!!!!");		
		for(int i = 0; i < WARMING_ITERATIONS; i++){
			System.out.println("WARMING ITERATION: " + i);
			runTest(controller, exec, WARMUP_DURATION_MS / WARMING_ITERATIONS);
		}
		
		
		//Real benchmark
		System.out.println("\n\n\n!!!!!!!!!!!!!!!!!  RUNNING REAL TEST !!!!!!!!!!!!!!!!!!!");
		initCounters();
		runTest(controller, exec, BENCHMARK_DURATION_MS);
		
		//Shutdown thread executor service
		exec.shutdownNow();

		printStatistics();
		
	}

	private static void runTest(VehicleController controller, ExecutorService exec, long duration) {
		CompletionService<TaskStatistics> ecs = new ExecutorCompletionService<>(exec);
		List<TaskStatistics> results = new ArrayList<>();

		//submit tasks for concurrent execution
		for (int i = 0; i < NUM_THREADS; i++){
			System.out.println("Submitting task: TASK-" + i);
			ecs.submit(new VehicleTask("TASK-" + i, controller, duration));
		}
		
		// Wait for completion and print individul results
		for (int i = 0; i < NUM_THREADS; ++i) {
			try {
				TaskStatistics nextStat = ecs.take().get();
				results.add(nextStat); //block till next task finishes
				updateCounters(nextStat);
				System.out.println(nextStat); //block till next task finishes
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void printStatistics() {
		//Print overall statistics
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nAVERAGE STATISTICS: [recordsAdded=").append((100 * recordsAdded / NUM_THREADS) / 100D)
				.append(", recordsDeleted=").append((100 * recordsDeleted/ NUM_THREADS) / 100D)
				.append(", recordsUpdated=").append((100 * recordsUpdated/ NUM_THREADS) / 100D)
				.append(", getOperations=").append((100 * getOperations/ NUM_THREADS) / 100D)
				.append(", bulkGetOperations=").append((100 * bulkGetOperations/ NUM_THREADS) / 100D)
				.append(", operationsPerThreadPerSecond=").append(((int)(100 * operationsPerSecond/ NUM_THREADS)) / 100D)
				.append("]");
		System.out.println(builder.toString());
	}

	private static void fillInitialRecords(VehicleController controller,
			int initialDbSize) {
		for(int i = 0; i < initialDbSize; i++){
			try{
				controller.add(makeRandomVehicle());
			} catch (grouper.controller.impl.inmemory.EntityExistsException ex){
				System.err.println(ex.getMessage());
			}
		}
	}
	
	private static void initCounters() {
		recordsAdded = 0;
		recordsDeleted = 0;
		recordsUpdated = 0;
		getOperations = 0;
		bulkGetOperations = 0;
		operationsPerSecond = 0;
	}
	
	private static void updateCounters(TaskStatistics stat) {
		recordsAdded += stat.getRecordsAdded();
		recordsDeleted += stat.getRecordsDeleted();
		recordsUpdated += stat.getRecordsUpdated();
		getOperations += stat.getGetOperations();
		bulkGetOperations += stat.getBulkGetOperations();
		operationsPerSecond += stat.getOperationsPerSecond();
	}
}
