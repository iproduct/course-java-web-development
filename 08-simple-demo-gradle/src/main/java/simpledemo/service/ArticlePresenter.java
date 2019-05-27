package simpledemo.service;

import simpledemo.model.Article;

public interface ArticlePresenter {
	void present();
	Article addArticle(Article article);
}
