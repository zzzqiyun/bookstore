package service;

import dao.AccountDaoImpl;
import dao.interfacedao.AccountDao;
import domain.Account;

/**
 * @author qiyunzhou
 * @date 2018/6/28
 * @time 15:25
 */
public class AccountService {
private AccountDao accountDao = new AccountDaoImpl();
public Account getAccount(int accountId){
  Account account = accountDao.get(accountId);
  return  account;
}
}
