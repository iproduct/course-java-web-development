package simpledemo.dao;

import java.util.Collection;
import java.util.Optional;

import simpledemo.model.Article;

public interface ArticleProvider {
	Collection<Article> getArticles();
	Optional<Article> getArticleById(long id);
	Article addArticle(Article article);
}
