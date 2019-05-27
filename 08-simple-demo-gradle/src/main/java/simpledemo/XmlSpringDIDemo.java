package simpledemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import simpledemo.service.ArticlePresenter;

public class XmlSpringDIDemo {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
		ArticlePresenter presenter = ctx.getBean("presenter", ArticlePresenter.class);
		presenter.present();
	}

}
