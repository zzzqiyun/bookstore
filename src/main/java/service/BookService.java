package service;

import dao.AccountDaoImpl;
import dao.BookDaoImpl;
import dao.TradeDaoImpl;
import dao.TradeItemDaoImpl;
import dao.UserDaoImpl;
import dao.interfacedao.AccountDao;
import dao.interfacedao.BookDao;
import dao.interfacedao.TradeDao;
import dao.interfacedao.TradeItemDao;
import dao.interfacedao.UserDao;
import domain.Account;
import domain.Book;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import domain.Trade;
import domain.TradeItem;
import domain.User;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import web.CriteriaBook;
import web.Page;

/**
 * @author qiyunzhou
 * @date 2018/6/18
 * @time 19:09
 */
public class BookService {

  private BookDao bookDao = new BookDaoImpl();

  public Page<Book> getBooks(CriteriaBook criteriaBook) {
    return bookDao.getPage(criteriaBook);
  }

  public Book getBook(Integer id) {
    return bookDao.getBook(id);
  }

  public Boolean addCart(Integer bookId, ShoppingCart sc) {
    Book book = bookDao.getBook(bookId);
    if (book != null) {
      sc.addBook(book);
      return true;
    }
    return false;
  }

  public void removeBook(Integer boookId, ShoppingCart sc) {
    sc.removeItem(boookId);
  }

  public void removeAllBook(ShoppingCart sc) {
    sc.clear();
  }

  public void updateItemQuantity(ShoppingCart sc, int bookId, int quantity) {
    sc.updateItemQuantity(bookId, quantity);
  }

  TradeItemDao tradeItemDao = new TradeItemDaoImpl();
  TradeDao tradeDao = new TradeDaoImpl();
  AccountDao accountDao = new AccountDaoImpl();
  UserDao userDao = new UserDaoImpl();

  //真正的业务方法
  public void cash(ShoppingCart sc, String userName, String accountId) {
    //1、更新数据库中的书本信息，salesAmount+?,bookStore-?
    bookDao.batchUpdateStoreNumberAndSalesAmount(sc.getItems());

    //2、更新account中的balance
    accountDao.updateBalance(Integer.parseInt(accountId),sc.getTotalMoney());

    //3、trade表中插入一条交易项信息
    Trade trade = new Trade();
    trade.setUserId(userDao.getUser(userName).getUserId());

    trade.setTradeTime(new Date(new java.util.Date().getTime()));
    tradeDao.insert(trade);

    //4、tradeItem表中插入多条交易信息
    Collection<TradeItem> tradeItems = new ArrayList<>();
    for (ShoppingCartItem sci:sc.getItems()) {
      TradeItem tradeItem = new TradeItem();
      tradeItem.setBookId(sci.getBook().getBookId());
      tradeItem.setQuantity(sci.getQuantity());
      tradeItem.setItemId(trade.getTradeId());
      tradeItems.add(tradeItem);
    }
    tradeItemDao.batchSave(tradeItems);
    //清空购物车
    sc.clear();
  }
}
