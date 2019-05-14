package grouper.task;

public class TaskStatistics {
	private String taskId;
	private long recordsAdded, recordsDeleted, recordsUpdated, getOperations, bulkGetOperations;
	private double operationsPerSecond;
	
	public TaskStatistics(String taskId, long recordsAdded, long recordsDeleted,
			long recordsUpdated, long getOperations, long bulkGetOperations,
			double operationsPerSecond) {
		this.taskId = taskId;
		this.recordsAdded = recordsAdded;
		this.recordsDeleted = recordsDeleted;
		this.recordsUpdated = recordsUpdated;
		this.getOperations = getOperations;
		this.bulkGetOperations = bulkGetOperations;
		this.operationsPerSecond = operationsPerSecond;
	}

	public long getRecordsAdded() {
		return recordsAdded;
	}
	
	public long getRecordsDeleted() {
		return recordsDeleted;
	}
	
	public long getRecordsUpdated() {
		return recordsUpdated;
	}
	
	public double getOperationsPerSecond() {
		return operationsPerSecond;
	}

	public long getGetOperations() {
		return getOperations;
	}

	public long getBulkGetOperations() {
		return bulkGetOperations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaskStatistics for "). append(taskId)
				.append(" [recordsAdded=").append(recordsAdded)
				.append(", recordsDeleted=").append(recordsDeleted)
				.append(", recordsUpdated=").append(recordsUpdated)
				.append(", getOperations=").append(getOperations)
				.append(", bulkGetOperations=").append(bulkGetOperations)
				.append(", operationsPerSecond=").append(operationsPerSecond)
				.append("]");
		return builder.toString();
	}	
	
}
