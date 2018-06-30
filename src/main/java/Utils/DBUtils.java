package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author qiyunzhou
 * @date 2018/5/9
 * @time 20:15
 */
public class DBUtils {
  static String driver = "com.mysql.jdbc.Driver";

  public static Connection getConnetion(String url,String userName,String password){
    try {
      Class.forName(driver);
      return DriverManager.getConnection(url,userName,password);
    }catch(ClassNotFoundException e){
      e.printStackTrace();
    }catch(SQLException e){
      e.printStackTrace();
    }
    return null;
  }


  public static void closeConnection(Connection connection){
    if(connection!=null){
      try {
        connection.close();
      }catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void relaese(Statement statement,ResultSet resultSet){
    if(statement!=null) {
      try {
        statement.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
      if(resultSet!=null) {
        try {
          resultSet.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
  }

}
