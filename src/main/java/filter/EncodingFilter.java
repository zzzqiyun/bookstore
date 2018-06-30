package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author qiyunzhou
 * @date 2018/6/25
 * @time 09:27
 */
public class EncodingFilter implements Filter {

  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp,
      FilterChain chain) throws ServletException, IOException {
    String encoding = filterConfig.getServletContext().getInitParameter("encoding");
    req.setCharacterEncoding(encoding);
    chain.doFilter(req,resp);
  }

  private FilterConfig filterConfig = null;

  public void init(FilterConfig config) throws ServletException {
    this.filterConfig = config;
  }

}
