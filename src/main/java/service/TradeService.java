package service;

import dao.TradeDaoImpl;
import dao.interfacedao.TradeDao;
import domain.Trade;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/6/30
 * @time 17:12
 */
public class TradeService {
  public Set<Trade> getTrades(int userId) {
    TradeDao tradeDao = new TradeDaoImpl();
     return tradeDao.getTradeWithUserId(userId);
  }
}

