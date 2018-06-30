package web;

import domain.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author qiyunzhou
 * @date 2018/6/21
 * @time 09:03
 */
public class BookStoreWebUtils {

  public static ShoppingCart getShoppingCart(HttpServletRequest request) {

    HttpSession session = request.getSession();

    ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
    if (sc == null) {
      sc = new ShoppingCart();
      session.setAttribute("ShoppingCart",sc);
    }
    return sc;
  }

}
