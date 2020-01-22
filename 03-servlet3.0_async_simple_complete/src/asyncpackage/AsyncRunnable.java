package asyncpackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

public class AsyncRunnable implements Runnable {

	AsyncContext ctx;

	public AsyncRunnable(AsyncContext ctx) {
		this.ctx = ctx;
	}

	public void run() {
		// Simulate a long-running task such as
		// calling a webservice, performing database
		// operation, or waiting for a server side
		// event (such as receiving a message in
		// Comet-enabled environment.

		// response is returned after an async. call is made
		ServletResponse response = ctx.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet NewServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Async call is made." + "</h1>");
			for (int i = 0; i < 10; i++) {
				out.printf("Message %d<br>", i + 1);
				out.flush();
				try {
					// Sleep for 1000 ms
					Thread.sleep(1000);
				} catch (Exception ie) {
				}
			}
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ctx.complete();
		}
	}
}
