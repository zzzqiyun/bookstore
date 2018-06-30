package dao;

import dao.interfacedao.TradeDao;
import domain.Trade;
import java.util.Date;
import java.util.Set;
import org.junit.Test;

/**
 * @author qiyunzhou
 * @date 2018/6/28
 * @time 16:10
 */
public class TradeDaoImplTest {
  TradeDao tradeDao = new TradeDaoImpl();
  @Test
  public void insert() {
    Trade trade = new Trade();
    trade.setTradeTime(new Date());
    trade.setUserId(3);
    tradeDao.insert(trade);
  }

  @Test
  public void getTradeWithUserId() {
    Set<Trade> tradeSets = tradeDao.getTradeWithUserId(2);
    for (Trade trade:tradeSets
    ) {
      System.out.println(trade);
    }
  }
}