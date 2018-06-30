package Servlet;

import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;
import dao.AccountDaoImpl;
import dao.interfacedao.AccountDao;
import domain.Account;
import domain.Book;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import domain.Trade;
import domain.User;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AccountService;
import service.BookService;
import service.TradeService;
import service.UserService;
import sun.jvm.hotspot.jdi.IntegerTypeImpl;
import web.BookStoreWebUtils;
import web.CriteriaBook;
import web.Page;

/**
 * @author qiyunzhou
 * @date 2018/6/18
 * @time 17:09
 */
public class BookServlet extends HttpServlet {

  private BookService bookService = new BookService();
  private AccountService accountService = new AccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String methodName = req.getParameter("method");
    try {
      Method method = getClass()
          .getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
      method.setAccessible(true);
      method.invoke(this, req, resp);
    } catch (Exception e) {
      //抛出异常
      throw new RuntimeException(e);
    }
  }

  protected void getBook(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String bookIdStr = req.getParameter("id");
    Integer bookId = -1;

    try {
      bookId = Integer.parseInt(bookIdStr);
    } catch (Exception e) {
    }

    Book book = null;

    if (bookId > 0)   //好处，避免id不合法时增加数据库的开销
    {
      book = bookService.getBook(bookId);
    }

    if (book == null) {
      resp.sendRedirect(req.getContextPath() + "error.jsp");
    }
    req.setAttribute("book", book);

    req.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(req, resp);

  }

  protected void getBooks(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String pageNoStr = req.getParameter("pageNo");
    String minPriceStr = req.getParameter("minPrice");
    String maxPriceStr = req.getParameter("maxPrice");

    int pageNo = 1;
    int minPrice = 0;
    int maxPrice = Integer.MAX_VALUE;
    try {
      pageNo = Integer.parseInt(pageNoStr);
    } catch (Exception e) {
    }
    try {
      minPrice = Integer.parseInt(minPriceStr);
    } catch (Exception e) {
    }
    try {
      maxPrice = Integer.parseInt(maxPriceStr);
    } catch (Exception e) {
    }
    CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
    Page<Book> pages = bookService.getBooks(criteriaBook);
    req.setAttribute("bookPage", pages);
    try {
      req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
    } catch (Exception e) {
    }
  }

  protected void addToCart(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //1、获取bookId
    String bookIdStr = req.getParameter("id");
    int bookId = -1;
    Boolean flag = false;
    try {
      bookId = Integer.parseInt(bookIdStr);
    } catch (Exception e) {
    }
    if (bookId > 0) {
      //2、 获取购物车对象
      ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
      //3、调用bookSevice的addCart方法把商品添加到购物车中
      flag = bookService.addCart(bookId, sc);
    }
    if (flag) {
      //4、直接调用getBooks()方法
      getBooks(req, resp);
      return;
    }
    resp.sendRedirect(req.getContextPath() + "error.jsp");
  }

  protected void forwardPage(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String page = req.getParameter("page");
    req.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp").forward(req, resp);
  }


  protected void removeItem(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String bookIdStr = req.getParameter("id");
    int bookId = -1;

    try {
      bookId = Integer.parseInt(bookIdStr);
    } catch (Exception e) {
    }
    ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);

    if (bookId > 0) {
      bookService.removeBook(bookId, sc);
      if (sc.isEmpty()) {
        req.getRequestDispatcher("/WEB-INF/pages/emptyCart.jsp").forward(req, resp);
        return;
      }
      req.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(req, resp);
      return;
    }
    resp.sendRedirect(req.getContextPath() + "error.jsp");
  }

  protected void removeAllItem(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
    if (sc != null) {
      bookService.removeAllBook(sc);
      req.getRequestDispatcher("/WEB-INF/pages/emptyCart.jsp").forward(req, resp);
    }
    resp.sendRedirect(req.getContextPath() + "error.jsp");
  }


  protected void updateItemQuantity(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //4、在method中获取 quantity,ID,获取购物车对象，调用service的方法进行修改
    String idStr = req.getParameter("bookId");
    String quantityStr = req.getParameter("quantity");

    int id = -1;
    int quantity = -1;
    try {
      id = Integer.parseInt(idStr);
      quantity = Integer.parseInt(quantityStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
    if (id > 0 && quantity > 0) {
      bookService.updateItemQuantity(sc, id, quantity);
    }

    //5、传回json数据：bookNumber:XXX,totalMoney:XXX
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("bookNumber", sc.getBookNumber());
    result.put("totalMoney", sc.getTotalMoney());

    Gson gson = new Gson();
    String gsonStr = gson.toJson(result);
    resp.setContentType("text/javascript");
    resp.getWriter().print(gsonStr);
  }

  private UserService userService = new UserService();

  protected void cash(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String userName = req.getParameter("userName");
    String accountId = req.getParameter("accountId");

    StringBuffer errors = validateFormFiled(userName, accountId);

    if (errors.toString().trim().equals("")) {

      //表单通过，验证user
      errors = validateUser(userName, accountId);

      if (errors.toString().equals("")) {

        //user通过，验证书本库存
        errors = validateBookNumber(req);

        if (errors.toString().equals("")) {

          //书本库存通过，验证余额
          errors = validateBalance(req, accountId);
        }
      }
    }
    if (!errors.toString().equals("")) {
      req.setAttribute("errors", errors);
      req.getRequestDispatcher("/WEB-INF/pages/checkOut.jsp").forward(req, resp);
    }
    bookService.cash(BookStoreWebUtils.getShoppingCart(req), userName, accountId);
    req.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(req, resp);
  }

  //1、简单验证，验证提交的表单中是否有空的属性
  public StringBuffer validateFormFiled(String userName, String accountId) {
    StringBuffer errors = new StringBuffer("");
    if (userName == null || userName.trim().equals("")) {
      errors.append("信用卡姓名不能为空<br>");
    }
    if (accountId == null || accountId.trim().equals("")) {
      errors.append("信用卡账户不能为空<br>");
    }
    return errors;
  }

  //2、验证提交的用户登陆信息与数据库中是否匹配
  public StringBuffer validateUser(String userName, String accountId) {
    Boolean fg = false;
    StringBuffer errors2 = new StringBuffer("");
    User user = userService.getUserByUserName(userName);
    if (user != null) {
      int accountId2 = user.getAccountId();
      if (accountId.trim().equals("" + accountId2)) {
        fg = true;
      }
    }
    if (!fg) {
      errors2.append("信用卡姓名和账号不匹配");
    }
    return errors2;
  }

  //3、验证书本的库存量是否充足
  public StringBuffer validateBookNumber(HttpServletRequest request) {
    StringBuffer errors = new StringBuffer("");
    ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
    for (ShoppingCartItem sci : sc.getItems()) {
      int itemQuantity = sci.getQuantity();
      //获取购物车中最新的书目的数量
      int storeBookNumber = bookService.getBook(sci.getBook().getBookId()).getStoreNumber();
      if (itemQuantity > storeBookNumber) {
        errors.append(sci.getBook().getTitle() + "库存不足<br>");
      }
    }
    return errors;
  }

  //4、验证余额是否充足
  public StringBuffer validateBalance(HttpServletRequest request, String accountId) {
    StringBuffer errors = new StringBuffer("");
    ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
    Account account = accountService.getAccount(Integer.parseInt(accountId));
    if (sc.getTotalMoney() > account.getBalance()) {
      errors.append("余额不足!");
    }
    return errors;
  }



}
