import dao.AccountDaoImpl;
import dao.interfacedao.AccountDao;
import domain.Account;
import org.junit.Test;

/**
 * @author qiyunzhou
 * @date 2018/6/27
 * @time 22:08
 */
public class TestAccountDaoImpl {
  AccountDao adi = new AccountDaoImpl();
  @Test
  public void testGet(){
    Account account = adi.get(1);
    System.out.println(account.getBalance());
  }

  @Test
  public void testUpdate(){
    adi.updateBalance(1,200);
  }

}
