package domain;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:22
 */
public class Account {
  private int accountId;
  private float  balance;

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }
}
