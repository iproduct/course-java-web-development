package bookstore.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@WebFilter(filterName="counter", servletNames = {"ShoppingServlet"}) 
@Slf4j
public class CounterFilter implements Filter {
	ServletContext ctx;
	AtomicLong counter = new AtomicLong(0L);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponseWrapper resWrap = new CharResponseWrapper((HttpServletResponse)response);
		chain.doFilter(request, resWrap);
		long count = counter.incrementAndGet();
		PrintWriter out = response.getWriter();
		out.write(resWrap.toString().substring(0,
				resWrap.toString().indexOf("</body>")-1));
			out.write("<p>" + "Visitor: " +     count);
			out.write("</p></body></html>");
			response.setContentLength(resWrap.toString().getBytes().length);
			out.close();
		HttpServletRequest r = (HttpServletRequest) request;
		log.info("COUNTER: " + count);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ctx = filterConfig.getServletContext();
	}

}

class CharResponseWrapper extends HttpServletResponseWrapper {
private CharArrayWriter output;
public String toString() {
    return output.toString();
}
public CharResponseWrapper(HttpServletResponse response){
    super(response);
    output = new CharArrayWriter();
}
public PrintWriter getWriter(){
    return new PrintWriter(output);
}
}

