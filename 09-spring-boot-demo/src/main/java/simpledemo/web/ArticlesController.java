package simpledemo.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simpledemo.model.Article;
import simpledemo.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
	@Autowired
	private ArticleService service;
	
	@GetMapping
	Collection<Article> getArticles() {
		return service.getArticles();
	}
	
	@GetMapping("{id}")
	ResponseEntity<Article> getArticles(@PathVariable long id) {
		return service.getArticleById(id)
				.map(a -> ResponseEntity.ok(a))
				.orElse(ResponseEntity.notFound().build());
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

}
