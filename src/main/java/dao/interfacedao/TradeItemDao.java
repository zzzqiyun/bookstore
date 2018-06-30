package dao.interfacedao;

import domain.TradeItem;
import java.util.Collection;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:14
 */
public interface TradeItemDao {

  /**
   * 批量保存item对象
   * @param items
   */
public abstract void batchSave(Collection<TradeItem> items);

/**
 * 根据TradeId获取和其关联的TradeItem集合
 * @param tradeId
 * @return
 */
public abstract Set<TradeItem> getTradeItemWithTradeId(int tradeId);
}
