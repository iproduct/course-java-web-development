package simpledemo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simpledemo.dao.ArticleProvider;
import simpledemo.dao.ArticlesRepository;
import simpledemo.model.Article;

@Service
public class MockArticleService implements ArticleService {
	private ArticlesRepository repo;

	@Autowired
	public MockArticleService(ArticlesRepository repository) {
		this.repo = repository;
	}

	@Override
	public Article addArticle(Article article) {
		return repo.insert(article);
	}

	@Override
	public Collection<Article> getArticles() {
		return repo.findAll();
	}

	@Override
	public Optional<Article> getArticleById(String id) {
		return repo.findById(id);
	}

	@Override
	public Optional<Article> deleteArticleById(String id) {
		return repo.removeById(id);
	}

	@Override
	public Article updateArticle(Article article) {
		return null;
	}

}
