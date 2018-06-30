package dao;

import dao.interfacedao.AccountDao;
import domain.Account;

/**
 * @author qiyunzhou
 * @date 2018/6/27
 * @time 16:22
 */
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

  @Override
  public Account get(Integer accountId) {
      String sql = "SELECT * FROM account where accountId =?";
        return (Account) query(sql,accountId);
  }

  @Override
  public void updateBalance(Integer accountId, float amount) {
    String sql = "update account set balance =balance - ? where accountId = ?";
    update(sql,amount,accountId);
  }
}
