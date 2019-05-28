package simpledemo.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import simpledemo.exception.InvalidRequestException;
import simpledemo.exception.NonexistingEntityException;
import simpledemo.model.Article;
import simpledemo.model.ErrorResponse;
import simpledemo.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
@Slf4j
public class ArticlesController {
	@Autowired
	private ArticleService service;
	
	@GetMapping
	Collection<Article> getArticles() {
		return service.getArticles();
	}
	
	@GetMapping("{id}")
	Article getArticle(@PathVariable String id) throws NonexistingEntityException {
		return service.getArticleById(id);
	}
	
	@PostMapping
	ResponseEntity<Article> addArticle(@RequestBody Article article) {
		Article createdArticle = service.addArticle(article);
		URI location = null;
		try {
			location = new URI("/api/articles/" + createdArticle.getId());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(location).body(createdArticle);
	}

	@DeleteMapping("{id}")
	Article deleteArticle(@PathVariable String id) throws NonexistingEntityException {
		return service.deleteArticleById(id);
	}
	
	@PutMapping("{id}")
	Article updateArticle(@PathVariable String id, @RequestBody Article article) 
			throws InvalidRequestException, NonexistingEntityException{
		if( !id.equalsIgnoreCase(article.getId()) ) {
			throw new InvalidRequestException("IDs in path (" + id + ") and article body (" 
					+ article.getId() + ") are different.");
		}
		return service.updateArticle(article);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleNonExistingEntityException(NonexistingEntityException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
	}
	

}
