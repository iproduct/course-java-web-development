package simpledemo.dao;

import java.util.List;

import simpledemo.model.Article;

public interface ArticleProvider {
	List<Article> getArticles();
}
