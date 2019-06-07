package bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bookstore.exception.EntityExistsException;
import bookstore.exception.EntityNotFoundException;
import bookstore.model.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Override
	public List<Author> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author getById(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author create(Author author) throws EntityExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author update(Author author) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author delete(int authorId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
