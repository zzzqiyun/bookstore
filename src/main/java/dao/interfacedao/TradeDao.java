package dao.interfacedao;

import domain.Trade;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:07
 */
public interface TradeDao {

  /**
   * 向数据库中插入Trade对象
   * @param trade
   */
public abstract void insert(Trade trade);

/**
 * 根据userId获取和其关联的Trade的集合
 * @param userId
 * @return
 */
public abstract Set<Trade> getTradeWithUserId(Integer userId);
}
