package forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;

	private int[] arr;
 
    private static final int THRESHOLD = 10;
    
    private static Logger logger = Logger.getAnonymousLogger();
 
    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }
 
    @Override
    protected Integer compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
              .stream()
              .mapToInt(ForkJoinTask::join)
              .sum();
        } else {
            return processing(arr);
        }
    }
 
    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }
 
    private Integer processing(int[] arr) {
        Integer result = Arrays.stream(arr)
          .filter(a -> a > 1 && a < 5)
          .map(a -> a * 10)
          .sum();
        logger.info("This result - (" + Arrays.toString(arr) + " --> " + result + ") - was processed by "
                + Thread.currentThread().getName());
        return result;
    }
}