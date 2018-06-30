package filter;

import Utils.DBUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.ConnectionContext;

/**
 * @author qiyunzhou
 * @date 2018/6/29
 * @time 19:42
 */
public class TranactionFilter implements Filter {

  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp,
      FilterChain chain) throws ServletException, IOException {
    Connection connection = null;
    try {
      //1、获取连接
      connection = DBUtils
          .getConnetion("jdbc:mysql://127.0.0.1:3306/bookstore?useSSL=false", "root", "zhouqiyun");

      //2、开启事物
      connection.setAutoCommit(false);

      //3、利用ThreadLocal将connection跟当前线程进行绑定
      ConnectionContext.getInstance().bind(connection);

      //4、将请求转到目标servlet进行处理
      chain.doFilter(req, resp);

      //5.提交事物
      connection.commit();

    } catch (Exception e) {
      //6、出异常回滚事物
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
      //出异常，页面跳转
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      response.sendRedirect(request.getContextPath()+"error.jsp");
    } finally {
      //7、解除绑定
      ConnectionContext.getInstance().removeBind();
      //8、关闭连接
        DBUtils.closeConnection(connection);
    }
  }

  public void init(FilterConfig config) throws ServletException {

  }

}
