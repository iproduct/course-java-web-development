package simpledemo.dao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import simpledemo.model.Article;

@Repository("provider")
public class MockArticleProvider implements ArticleProvider {
	private List<Article> articles = new CopyOnWriteArrayList<>();
	
	public MockArticleProvider() {
		articles = new CopyOnWriteArrayList<>(Arrays.asList(
				new Article("Welcome to Spring 5", "Spring 5 is great because ..."),
				new Article("Dependency Injection", "Should I use DI or lookup ..."),
				new Article("Spring Beans and Wireing", "There are several way to wire Spring beans ...")
			));
	}
	
	@Override
	public List<Article> getArticles() {
		return articles;
	}

	@Override
	public Article addArticle(Article article) {
		articles.add(article);
		return article;
	}
}
