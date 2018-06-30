package dao.interfacedao;

import domain.Account;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:20
 */
public interface AccountDao {
  /**
   * 根据accountId获取account对象
   * @param accountId
   * @return
   */
public abstract Account get(Integer accountId);

/**
 *根据传入的accountId,amount更新指定的账户的的余额：扣除amount的钱数
 * @param accountId
 * @return
 */
public abstract void updateBalance(Integer accountId,float amount);
}
