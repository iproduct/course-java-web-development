package invoicing.dao;

public interface IdAutoIncrementable<K> {
	K getNextId();
	void setId(K id);
}
