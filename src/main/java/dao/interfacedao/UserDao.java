package dao.interfacedao;

import domain.User;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:04
 */
public interface UserDao {
  /**
   * 根据用户名获取User对象
   */

  public abstract User getUser(String name);

}
