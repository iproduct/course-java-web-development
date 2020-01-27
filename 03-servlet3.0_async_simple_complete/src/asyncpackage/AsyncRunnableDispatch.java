package asyncpackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

public class AsyncRunnableDispatch implements Runnable {

	AsyncContext ctx;

	public AsyncRunnableDispatch(AsyncContext ctx) {
		this.ctx = ctx;
	}

	public void run() {
		// Simulate a long-running task such as
		// calling a webservice, performing database
		// operation, or waiting for a server side
		// event (such as receiving a message in
		// Comet-enabled environment.
		try {
			// Sleep for 5000 ms
			Thread.sleep(5000);
		} catch (Exception ie) {
		}

		ctx.dispatch("/WEB-INF/jsp/response.jsp");
	}
}