package asyncpackage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

@WebServlet(urlPatterns = "/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
//	ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AsyncContext aCtx = request.startAsync(request, response);

        aCtx.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("----onComplete() is called");
            }

            public void onTimeout(AsyncEvent event) throws IOException {
                System.out.println("----onTimeout() is called");

            }

            public void onError(AsyncEvent event) throws IOException {
                System.out.println("----onError() is called");

            }

            public void onStartAsync(AsyncEvent event) throws IOException {         
                System.out.println("----onStartAsync() is called");
            }
        });
        
//        aCtx.start(new AsyncRunnableDispatch(aCtx));
        
        aCtx.start(new AsyncRunnable(aCtx));
        
//      executor.execute(new AsyncRunnable(aCtx));

    }
}
