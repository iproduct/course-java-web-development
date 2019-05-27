package simpledemo.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import simpledemo.model.Article;

@Repository("provider")
public class MockArticleProvider implements ArticleProvider {
	private AtomicLong sequence = new AtomicLong(0);
	private Map<Long, Article> articles = new ConcurrentHashMap<>();
	
	public MockArticleProvider() {
		Arrays.asList(
				new Article("Welcome to Spring 5", "Spring 5 is great because ..."),
				new Article("Dependency Injection", "Should I use DI or lookup ..."),
				new Article("Spring Beans and Wireing", "There are several way to wire Spring beans ...")
			).stream().forEach(this::addArticle);
		
	}
	
	@Override
	public Collection<Article> getArticles() {
		return articles.values();
	}

	@Override
	public Article addArticle(Article article) {
		article.setId(sequence.incrementAndGet());
		articles.put(article.getId(), article);
		return article;
	}

	@Override
	public Optional<Article> getArticleById(long id) {
		return Optional.ofNullable(articles.get(id));
	}
}
