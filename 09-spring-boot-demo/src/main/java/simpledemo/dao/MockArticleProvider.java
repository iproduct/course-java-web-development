package simpledemo.dao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import simpledemo.model.Article;

@Repository("provider")
public class MockArticleProvider implements ArticleProvider {
	private AtomicLong sequence = new AtomicLong(0);
	private List<Article> articles = new CopyOnWriteArrayList<>();
	
	public MockArticleProvider() {
		Arrays.asList(
				new Article("Welcome to Spring 5", "Spring 5 is great because ..."),
				new Article("Dependency Injection", "Should I use DI or lookup ..."),
				new Article("Spring Beans and Wireing", "There are several way to wire Spring beans ...")
			).stream().forEach(this::addArticle);
		
	}
	
	@Override
	public List<Article> getArticles() {
		return articles;
	}

	@Override
	public Article addArticle(Article article) {
		article.setId(sequence.incrementAndGet());
		articles.add(article);
		return article;
	}
}
