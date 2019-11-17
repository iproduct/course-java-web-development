package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {

	public static void main(String[] args) {
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
//		commonPool.invoke(new CustomRecursiveAction("Submitting Tasks to the ForkJoinPool"));
		int result = commonPool.invoke(new CustomRecursiveTask(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3, 4,4,4,4,4,4,4, 5,5,5,5,5,5,5,5,5}));
		System.out.println(result);
	}

}
