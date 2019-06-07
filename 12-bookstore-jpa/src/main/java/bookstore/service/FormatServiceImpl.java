package bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bookstore.exception.EntityExistsException;
import bookstore.exception.EntityNotFoundException;
import bookstore.model.Format;

@Service
public class FormatServiceImpl implements FormatService {

	@Override
	public List<Format> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Format getById(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Format create(Format format) throws EntityExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Format update(Format format) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Format delete(int formatId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
