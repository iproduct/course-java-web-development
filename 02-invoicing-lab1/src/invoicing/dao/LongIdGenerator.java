package invoicing.dao;

public class LongIdGenerator implements IdGenerator<Long> {
	private static long nextId = 0;
	
	@Override
	public Long getNextId() {
		return ++nextId;
	}

}
