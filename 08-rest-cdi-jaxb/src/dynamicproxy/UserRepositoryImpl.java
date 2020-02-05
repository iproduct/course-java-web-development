package dynamicproxy;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepositoryImpl implements UserRepository{
	Map<Long, User> users = new ConcurrentHashMap<Long, User>();
	AtomicLong sequence = new AtomicLong();

	@Override
	public Collection<User> findAll() {
		try {
			Thread.sleep(120);
		} catch (InterruptedException e) {}
		return users.values();
	}

	@Override
	public User addUser(User user) {
		try {
			Thread.sleep(99);
		} catch (InterruptedException e) {}
		user.setId(sequence.incrementAndGet());
		users.put(user.getId(), user);
		return user;
	}
	
}
