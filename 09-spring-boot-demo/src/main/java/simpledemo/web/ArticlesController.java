package simpledemo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	List<Article> getArticles(){
		return service.getArticles();
	}
	
	@PostMapping
	Article addArticle(@RequestBody Article article) {
		return service.addArticle(article);
	}

}
