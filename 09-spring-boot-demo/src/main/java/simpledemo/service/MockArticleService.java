package simpledemo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simpledemo.dao.ArticleProvider;
import simpledemo.model.Article;

@Service
public class MockArticleService implements ArticleService {
	private ArticleProvider provider;

	@Autowired
	public MockArticleService(ArticleProvider provider) {
		this.provider = provider;
	}

	@Override
	public Article addArticle(Article article) {
		return provider.addArticle(article);
	}

	@Override
	public Collection<Article> getArticles() {
		return provider.getArticles();
	}

	@Override
	public Optional<Article> getArticleById(long id) {
		return provider.getArticleById(id);
	}

}
