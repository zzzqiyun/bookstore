package domain;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 13:53
 */
public class User {
  private int userId;
  private String userName;
  private int accountId;
  private Set<Trade> trades = new LinkedHashSet<>();

  public Set<Trade> getTrades() {
    return trades;
  }

  public void setTrades(Set<Trade> trades) {

    this.trades = trades;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", userName='" + userName + '\'' +
        ", accountId=" + accountId +
        '}';
  }
}
