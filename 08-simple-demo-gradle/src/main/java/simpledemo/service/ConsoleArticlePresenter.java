package simpledemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simpledemo.dao.ArticleProvider;
import simpledemo.model.Article;

@Service("presenter")
public class ConsoleArticlePresenter implements ArticlePresenter {
	private ArticleProvider provider;

	@Autowired
	public ConsoleArticlePresenter(ArticleProvider provider) {
		this.provider = provider;
	}

	@Override
	public void present() {
		provider.getArticles().forEach(System.out::println);
	}

	@Override
	public Article addArticle(Article article) {
		return provider.addArticle(article);
	}

}
