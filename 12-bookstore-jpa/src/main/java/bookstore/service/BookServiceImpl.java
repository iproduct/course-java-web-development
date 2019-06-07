package bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bookstore.exception.EntityExistsException;
import bookstore.exception.EntityNotFoundException;
import bookstore.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getById(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book create(Book book) throws EntityExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book update(Book book) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book delete(int bookId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
