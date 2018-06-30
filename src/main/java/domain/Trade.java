package domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:10
 */
public class Trade {
  private int tradeId;
  private Date tradeTime;
  private int userId;

  public Set<TradeItem> getItems() {
    return items;
  }

  private Set<TradeItem> items = new LinkedHashSet<>();

  public void setItems(Set<TradeItem> items) {
    this.items = items;
  }

  public int getTradeId() {
    return tradeId;
  }

  public void setTradeId(int tradeId) {
    this.tradeId = tradeId;
  }

  public Date getTradeTime() {
    return tradeTime;
  }

  public void setTradeTime(Date tradeTime) {
    this.tradeTime = tradeTime;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Trade{" +
        "tradeId=" + tradeId +
        ", tradeTime=" + tradeTime +
        ", userId=" + userId +
        '}';
  }
}
