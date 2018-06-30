package dao;

import Utils.DBUtils;
import Utils.ReflectionUtils;
import dao.interfacedao.Dao;
import domain.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import web.ConnectionContext;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 13:43
 */
public class BaseDao<T> implements Dao<T> {
  static String url = "jdbc:mysql://127.0.0.1:3306/bookstore?useSSL=false";
  static String userName = "root";
  static String password = "zhouqiyun";


  private QueryRunner queryRunner = new QueryRunner();
  private Class<T> clazz;

  public BaseDao(){
    //通过泛型的类型 获得相映的类
   clazz = ReflectionUtils.getSuperGenericType(getClass());
  }

  public long insert(String sql, Object... args) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    long id = 0;

    try{
      connection = ConnectionContext.getInstance().getConnection();
      //可以标识需要产生主键
      preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      //存放占位符
      if(args!=null){
        for(int i=0;i<args.length;i++){
          //从1开始放占位符
          preparedStatement.setObject(i+1,args[i]);
        }
      }
      //执行更新操作
      preparedStatement.executeUpdate();

      //怎么产生主键呢？获取主键的值
      resultSet = preparedStatement.getGeneratedKeys();

      if(resultSet.next()){
        id =resultSet.getLong(1);//以long 的形式获取此 ResultSet 对象的当前行中指定列的值
      }
    }catch(Exception e){
    e.printStackTrace();
    }finally{
      DBUtils.relaese(preparedStatement,resultSet);
    }
    return id;
  }

  public void update(String sql, Object... args) {

    Connection connection = null;
    try{
      connection = ConnectionContext.getInstance().getConnection();
      queryRunner.update(connection,sql,args);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public Object query(String sql, Object... args) {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet= null;

    //Class clazz = Book.class;
    Object obj = null;
    Connection connection = null;
    try {

      obj = clazz.newInstance();
      connection = ConnectionContext.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(sql);
      //为占位符赋值
      if (args != null) {
        for (int i = 0; i < args.length; i++) {
          preparedStatement.setObject(i + 1, args[i]);
        }

        resultSet = preparedStatement.executeQuery();

        //获取ResultSetMetaData对象
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //获取map对象
        Map<String, Object> map = new HashMap<String, Object>();

        while (resultSet.next()) {
          for (int i = 0; i < rsmd.getColumnCount(); i++) {
            String columnLabel = rsmd.getColumnLabel(i + 1);
            Object labelValue = resultSet.getObject(columnLabel);
            map.put(columnLabel, labelValue);
          }
        }

        //遍历集合元素

        for (Map.Entry<String, Object> entry : map.entrySet()) {
          String fieldName = entry.getKey();
          Object fileValue = entry.getValue();
          ReflectionUtils.setFieldValue(obj, fieldName, fileValue);
        }

       // return obj;
        return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);
      }

    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }


  @Override
  public List<T> queryForList(String sql, Object... args) {
    Connection connection = null;
    try{
      connection = ConnectionContext.getInstance().getConnection();
      return  queryRunner.query(connection,sql,new BeanListHandler<>(clazz),args);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public <V> V getSingleVal(String sql, Object... args) {

    Connection connection = null;
    try{
    connection =ConnectionContext.getInstance().getConnection();
    return (V)queryRunner.query(connection,sql,new ScalarHandler(),args);
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public void batch(String sql, Object[]... args) {


    Connection connection = null;
    try{
      connection = ConnectionContext.getInstance().getConnection();
      queryRunner.batch(connection,sql,args);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
