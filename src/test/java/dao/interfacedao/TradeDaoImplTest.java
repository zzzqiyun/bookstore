package dao.interfacedao;

import dao.TradeItemDaoImpl;
import domain.TradeItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 * @author qiyunzhou
 * @date 2018/6/28
 * @time 21:22
 */
public class TradeDaoImplTest {
TradeItemDao tradeDao = new TradeItemDaoImpl();
  @Test
  public void batchSave() {
    List<TradeItem> tradeItems = new ArrayList<>();
    tradeItems.add(new TradeItem(1,34,25));
    tradeItems.add(new TradeItem(34,31,25));
    tradeItems.add(new TradeItem(13,33,25));
    tradeItems.add(new TradeItem(14,32,25));

    tradeDao.batchSave(tradeItems);
  }

  @Test
  public void testGetTradeItemWithTradeId() {
    Set<TradeItem> tradeItems =tradeDao.getTradeItemWithTradeId(2);
      System.out.println(tradeItems);
  }
}