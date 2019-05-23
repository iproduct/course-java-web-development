package simpledemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import simpledemo.service.ArticlePresenter;

public class AnnotationSpringDIDemo {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("simpledemo");
		ArticlePresenter presenter = ctx.getBean("presenter", ArticlePresenter.class);
		System.out.println("Anntoations Di Demo");
		presenter.present();
	}

}
