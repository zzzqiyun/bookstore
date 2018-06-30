package Servlet;

import domain.Trade;
import domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.TradeService;
import service.UserService;

/**
 * @author qiyunzhou
 * @date 2018/6/30
 * @time 17:21
 */
public class UserServlet extends HttpServlet {
private UserService userService = new UserService();
  private TradeService tradeService = new TradeService();


  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String userName = req.getParameter("userName");

    //调用userService获取装配好trades的user对象
    User user = userService.getUserWithUseTrades(userName);

    if(user==null){
      resp.sendRedirect(req.getServletPath()+"error.jsp");
      return;
    }

    req.setAttribute("user",user);
    System.out.println("is have user:"+user);
    req.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(req,resp);
  }


}
