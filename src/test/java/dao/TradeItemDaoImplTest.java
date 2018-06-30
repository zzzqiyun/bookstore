package dao;

import static org.junit.Assert.*;

import dao.interfacedao.TradeDao;
import dao.interfacedao.TradeItemDao;
import domain.Trade;
import domain.TradeItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 * @author qiyunzhou
 * @date 2018/6/29
 * @time 08:54
 */
public class TradeItemDaoImplTest {
  TradeItemDao tradeItemDao = new TradeItemDaoImpl();
  TradeDao tradeDao = new TradeDaoImpl();

  @Test
  public void batchSave() {
    Collection<TradeItem> items = new ArrayList<>();
    items.add(new TradeItem(1,32,5));
    items.add(new TradeItem(3,33,5));
    items.add(new TradeItem(34,2,5));
    items.add(new TradeItem(100,1,5));
    tradeItemDao.batchSave(items);
  }

  @Test
  public void getTradeItemWithTradeId() {
    Set<TradeItem> trades =tradeItemDao.getTradeItemWithTradeId(2);
    System.out.println(trades);
  }

}