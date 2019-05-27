package simpledemo.service;

import java.util.List;

import simpledemo.model.Article;

public interface ArticleService {
	List<Article> getArticles();
	Article addArticle(Article article);
}
