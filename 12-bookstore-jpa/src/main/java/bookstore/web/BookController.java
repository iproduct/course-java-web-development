package bookstore.web;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import bookstore.exception.EntityExistsException;
import bookstore.model.Book;
import bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("books")
@Slf4j
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping
	private String getBooksList(Model model) {
		model.addAttribute("books", bookService.getAll());
		return "books-list";
	}
	
    @PostMapping(params = "edit")
    public String editBook(@RequestParam("edit") int editId, Model model, UriComponentsBuilder uriBuilder){
        log.info("Editing book: " + editId);
        URI uri = uriBuilder.path("/books/book-form").query("mode=edit&bookId={id}").buildAndExpand(editId).toUri();
        return "redirect:" + uri.toString();
    }

    @PostMapping(params = "delete")
    public String deleteBook(@RequestParam("delete") int deleteId, Model model){
        log.info("Deleting book: " + deleteId);
        bookService.delete(deleteId);
        return "redirect:/books";
    }

    @GetMapping("/book-form")
    public String getBookForm(@ModelAttribute ("book") Book book, ModelMap model,
                                @RequestParam(value="mode", required=false) String mode,
                                @RequestParam(value="bookId", required=false) Integer bookId){
        String title = "Add New Book";
        final String viewName = "book-form";
        if("edit".equals(mode)) {
             Book found = bookService.getById(bookId);
             model.addAttribute("book", found);
             title = "Edit Book";
        }
        
        model.addAttribute("path", viewName);
        model.addAttribute("title", title);
        return  viewName;
    }

    @PostMapping("/book-form")
    public String addBook(@Valid @ModelAttribute ("book") Book book,
                             BindingResult errors,
//                             @RequestParam(name = "cancel", required = false) String cancelBtn,
//                             @RequestParam("file") MultipartFile file,
                             Model model) throws EntityExistsException {
//        if(cancelBtn != null) return "redirect:/books";
        if(errors.hasErrors()) {
            List<String> errorMessages = errors.getAllErrors().stream().map(err -> {
                ConstraintViolation<Book> cv = err.unwrap(ConstraintViolation.class);
                return String.format("Error in '%s' - invalid value: '%s'", cv.getPropertyPath(), cv.getInvalidValue());
            }).collect(Collectors.toList());
            model.addAttribute("myErrors", errorMessages);
            return "book-form";
        } else {
            log.info("POST Book: " + book);
            if (book.getId() == 0) {
                log.info("ADD New Book: " + book);
                bookService.create(book);
            } else {
                log.info("UPDATE Book: " + book);
                bookService.update(book);
            }
            return "redirect:/books";
        }
    }

	
}
