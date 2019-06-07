package bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bookstore.exception.EntityExistsException;
import bookstore.exception.EntityNotFoundException;
import bookstore.model.Publisher;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Override
	public List<Publisher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher getById(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher create(Publisher publisher) throws EntityExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher update(Publisher publisher) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher delete(int publisherId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
