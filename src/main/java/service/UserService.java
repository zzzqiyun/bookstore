package service;

import dao.BookDaoImpl;
import dao.TradeDaoImpl;
import dao.TradeItemDaoImpl;
import dao.UserDaoImpl;
import dao.interfacedao.BookDao;
import dao.interfacedao.TradeDao;
import dao.interfacedao.TradeItemDao;
import dao.interfacedao.UserDao;
import domain.Trade;
import domain.TradeItem;
import domain.User;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/6/28
 * @time 09:19
 */
public class UserService {

  private UserDao userDao = new UserDaoImpl();
  private TradeDao tradeDao = new TradeDaoImpl();
  private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
  private BookDao bookDao = new BookDaoImpl();

  public User getUserByUserName(String userName) {
    return userDao.getUser(userName);
  }

  public User getUserWithUseTrades(String userName) {

    //调用UserDao的getUser(userName)方法获取user对象
    User user = userDao.getUser(userName);
    if (user == null) {
      return null;
    }

    //调用TradeDao的方法获取trades集合并且为其装配好user属性
    Set<Trade> trades = tradeDao.getTradeWithUserId(user.getUserId());

    if (trades != null) {
      for (Trade trade : trades) {
        int tradeIde = trade.getTradeId();
        Set<TradeItem> tradeItems = tradeItemDao.getTradeItemWithTradeId(tradeIde);

        if(tradeItems!=null){
          for (TradeItem tradeItem: tradeItems) {
            tradeItem.setBook(bookDao.getBook(tradeItem.getBookId()));
          }
          trade.setItems(tradeItems);
        }
      }
    }

    user.setTrades(trades);

    return user;
  }
}
