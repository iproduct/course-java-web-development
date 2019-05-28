package simpledemo.service;

import java.util.Collection;
import java.util.Optional;

import simpledemo.exception.EntityExistsException;
import simpledemo.exception.NonexistingEntityException;
import simpledemo.model.Article;

public interface ArticleService {
	Collection<Article> getArticles();
	Article getArticleById(String id) throws NonexistingEntityException;
	Article deleteArticleById(String id)  throws NonexistingEntityException;
	Article addArticle(Article article);
	Article updateArticle(Article article) throws NonexistingEntityException;
}
