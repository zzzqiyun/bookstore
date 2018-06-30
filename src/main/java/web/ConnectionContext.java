package web;

import java.sql.Connection;

/**
 * @author qiyunzhou
 * @date 2018/6/29
 * @time 19:53
 */
public class ConnectionContext {
  private ConnectionContext(){}

  private static ConnectionContext instance = new ConnectionContext();

  public static ConnectionContext getInstance() {
    return instance;
  }

  private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

  public void bind(Connection connection){
    connectionThreadLocal.set(connection);
  }
  public Connection getConnection(){
    return connectionThreadLocal.get();
  }

  public void removeBind(){
    connectionThreadLocal.remove();
  }
}
