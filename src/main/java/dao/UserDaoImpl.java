package dao;

import dao.interfacedao.UserDao;
import domain.User;

/**
 * @author qiyunzhou
 * @date 2018/6/27
 * @time 22:29
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao{

  @Override
  public User getUser(String name) {
    String sql = "select * from bookstore.userInfo where userName = ?";
    return (User) query(sql,name);
  }
}
