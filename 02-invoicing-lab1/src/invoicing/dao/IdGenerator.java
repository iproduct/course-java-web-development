package invoicing.dao;

public interface IdGenerator<K> {
	K getNextId();
}
