package dao;

import dao.interfacedao.TradeDao;
import domain.Trade;
import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/6/28
 * @time 15:57
 */
public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {

  @Override
  public void insert(Trade trade) {
    String sql = "INSERT INTO bookstore.trade(tradeTime,userId) VALUES (?,?)";
    long tradeId = insert(sql,trade.getTradeTime(),trade.getUserId());
    trade.setTradeId((int)tradeId);
  }

  @Override
  public Set<Trade> getTradeWithUserId(Integer userId) {
    String sql = "select * from bookstore.trade where userId =? order by tradeTime DESC";
    return  new LinkedHashSet<>(queryForList(sql,userId));
  }
}
