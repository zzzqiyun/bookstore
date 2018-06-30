package dao.interfacedao;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:46
 */

import domain.Book;
import java.util.List;

/**
 * Dao接口，定义dao的基本操作，由BaseDao提供实现
 * @param <T>:定义操作的泛型类型
 */
public interface Dao<T> {

  /**
   *执行INSERT操作，返回插入记录后的记录的Id
   * @param sql:代执行的SQL语句
   * @param args:填充占位符的可变参数
   * @retrun:插入新纪录的id
   */
  long insert(String sql,Object ... args);

  /**
   *执行UPDATE操作，包括INSERT(但没有返回值)，UPDATE，DELETE
   * @param sql:代执行的SQL语句
   * @param args:填充占位符的可变参数
   */
  void update(String sql,Object ... args);

  /**
   *执行单条记录的查询操作，返回与记录对应的一个对象
   * @param sql:代执行的SQL语句
   * @param args:填充占位符的可变参数
   * @return :与记录对应的一个对象
   */
  public Object query(String sql,Object ... args);

  /**
   * 执行多条记录的查询操作，返回与记录对应的类的一个List
   * @param sql
   * @param args
   * @return
   */
  List<T> queryForList(String sql,Object ... args);

  /**
   * 执行一个属性或值的查询操作，例如查询一条记录的某个字段，或查询某个统计信息，返回要查询的值
   * @param sql
   * @param args
   * @param <V>
   * @return
   */
  <V> V getSingleVal(String sql,Object ...args);

  /**
   * 执行批量更新操作
   * @param sql
   * @param args
   */
  void batch(String sql,Object[] ...args);
}
