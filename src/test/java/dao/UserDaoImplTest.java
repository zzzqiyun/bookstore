package dao;

import static org.junit.Assert.*;

import dao.interfacedao.UserDao;
import domain.User;
import org.junit.Test;

/**
 * @author qiyunzhou
 * @date 2018/6/27
 * @time 22:35
 */
public class UserDaoImplTest {
UserDao userDao = new UserDaoImpl();
  @Test
  public void getUSer() {
    User user = userDao.getUser("xiao");
    System.out.println(user);
  }
}