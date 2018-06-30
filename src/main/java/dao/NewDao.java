package dao;

import Utils.DBUtils;
import Utils.ReflectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiyunzhou
 * @date 2018/5/14
 * @time 11:31
 * JDBC编写Dao可能包含的方法
 */
public class NewDao {
  public NewDao(){ }

  static String url = "jdbc:mysql://127.0.0.1:3306/bookstore?useUnicode=true&characterEncoding=utf-8&";
  static String userName = "root";
  static String password = "zhouqiyun";

  //insert  update delete方法
  public void update (String sql,Object...args){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try{
     connection = DBUtils.getConnetion(url,userName,password);
     preparedStatement = connection.prepareStatement(sql);

        for(int i=0;i<args.length;i++){
          preparedStatement.setObject(i+1,args[i]);
        }
      preparedStatement.executeUpdate();

    }catch(Exception e){
      e.printStackTrace();
    }finally{
      DBUtils.relaese(preparedStatement,null);
      DBUtils.closeConnection(connection);
    }
  }


  //查询一条记录，返回对应的对象
  public  <T> T getObject(Class<T> clazz, String sql,Object...args){

     T entity = null;
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
    try{
       connection = DBUtils.getConnetion(url,userName,password);
       preparedStatement = connection.prepareStatement(sql);
         for(int i=0;i<args.length;i++){
           preparedStatement.setObject(i+1,args[i]);
         }

       resultSet = preparedStatement.executeQuery();

       while(resultSet.next()) {

         ResultSetMetaData rsmd = resultSet.getMetaData();
         Map<String, Object> map = new HashMap<>();

         for (int i = 0; i <rsmd.getColumnCount(); i++) {
           String labelName = rsmd.getColumnName(i + 1);
           Object labelValue = resultSet.getObject(i + 1);
           map.put(labelName, labelValue);
         }
         //利用反射创建对象
         entity = clazz.newInstance();

         //遍历map集合
         for (Map.Entry<String, Object> m : map.entrySet()) {
           String protityName = m.getKey();
           Object protityValue = m.getValue();
           //利用反射填充属性值
           ReflectionUtils.setFieldValue(entity, protityName, protityValue);
           //BeanUtils.setProperty(entity,protityName,protityValue);
         }
       }
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      DBUtils.relaese(preparedStatement,resultSet);
      DBUtils.closeConnection(connection);
    }
    return entity;
   }

   //查询符合条件的对象集合
  public <T> List<T> getForList(Class<T> clazz,String sql,Object...args){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<T> list = new ArrayList<>();
    List<Map<String,Object>> values = new ArrayList<>();

    try{
      connection = DBUtils.getConnetion(url,userName,password);
      preparedStatement = connection.prepareStatement(sql);

      for(int i =0;i<args.length;i++){
        preparedStatement.setObject(i+1,args[i]);
      }
      resultSet = preparedStatement.executeQuery();
      ResultSetMetaData rsmd = resultSet.getMetaData();
      Map<String,Object> map =null;

      while(resultSet.next()){

         map = new HashMap<>();

        for(int i=0;i<rsmd.getColumnCount();i++){
          String labelName = rsmd.getCatalogName(i+1);
          Object columnValue = resultSet.getObject(i+1);
          map.put(labelName,columnValue);
        }
        values.add(map);
      }


      //判断list是否为空，若不为空则遍历list
      //得到一个一个的map对象，再把一个map对象转为一个class
      T bean = null;
      if(values.size()>0) {
        for (Map<String, Object> m : values) {

          bean = clazz.newInstance();

          for(Map.Entry<String ,Object> me :m.entrySet()){
            String propertyName = me.getKey();
            Object  propertyValue = me.getValue();
            ReflectionUtils.setFieldValue(bean,propertyName,propertyValue);
          }
          list.add(bean);
        }
      }

    }catch(Exception e){
      e.printStackTrace();
    }finally{
      DBUtils.relaese(preparedStatement,resultSet);
      DBUtils.closeConnection(connection);
    }

    return list;
  }

  public <V> V getVal(String sql,Object...args){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DBUtils.getConnetion(url,userName,password);
      preparedStatement = connection.prepareStatement(sql);

      for(int i =0;i<args.length;i++){
        preparedStatement.setObject(i+1,args[i]);
      }
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        return  (V)resultSet.getObject(1);
      }
    return null;
  }catch(Exception e ){
      e.printStackTrace();
    }
    return null;
  }
}
