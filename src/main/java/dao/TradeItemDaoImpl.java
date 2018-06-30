package dao;

import dao.interfacedao.TradeItemDao;
import domain.TradeItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/6/28
 * @time 21:21
 */
public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

  @Override
  public void batchSave(Collection<TradeItem> items) {
    String sql = "INSERT INTO bookstore.tradeItem(quantity, bookId,tradeId) VALUES (?,?,?)";
    List<TradeItem> items1 = new ArrayList<>(items);
    Object[][] params = null;
    params = new Object[items.size()][3];
    for(int i = 0;i<items.size();i++){
      params[i][0]= items1.get(i).getQuantity();
      params[i][1]= items1.get(i).getBookId();
      params[i][2]= items1.get(i).getItemId();
    }
    batch(sql,params);
  }

  @Override
  public Set<TradeItem> getTradeItemWithTradeId(int tradeId) {
    String sql = "select itemId,quantity,bookId,tradeId from tradeItem where tradeId = ?";
    return new HashSet<>(queryForList(sql,tradeId));
  }
}
