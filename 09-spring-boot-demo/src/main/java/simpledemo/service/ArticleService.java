package simpledemo.service;

import java.util.Collection;
import java.util.Optional;

import simpledemo.model.Article;

public interface ArticleService {
	Collection<Article> getArticles();
	Optional<Article> getArticleById(long id);
	Article addArticle(Article article);
}
