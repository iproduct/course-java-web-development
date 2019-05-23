package simpledemo.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import simpledemo.model.Article;

@Repository
public class MockArticleProvider implements ArticleProvider {

	@Override
	public List<Article> getArticles() {
		return Arrays.asList(
			new Article("Welcome to Spring 5", "Spring 5 is great because ..."),
			new Article("Dependency Injection", "Should I use DI or lookup ..."),
			new Article("Spring Beans and Wireing", "There are several way to wire Spring beans ...")
		);
	}
}
