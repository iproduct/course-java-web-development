package shopping;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="profiler",servletNames = {"ShoppingServlet"}) 
//dispatcherTypes={DispatcherType.REQUEST})
public class ProfilingFilter implements Filter {
	ServletContext ctx;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		long start = System.nanoTime();
		chain.doFilter(request, response);
		long end = System.nanoTime();
		HttpServletRequest r = (HttpServletRequest) request;
		System.out.println("PROFILING " + r.getServletPath()
				+ " with METHOD " + r.getMethod() + ": Request processing time =  "
				+ (end - start) + " ns");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ctx = filterConfig.getServletContext();
	}

}
