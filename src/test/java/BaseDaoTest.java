import Utils.DBUtils;
import dao.BookDaoImpl;
import domain.Book;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;
import web.ConnectionContext;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 14:46
 */
public class BaseDaoTest {
  private  String url = "jdbc:mysql://127.0.0.1:3306/bookstore?useSSL=false";
  private  String userName= "root";
  private String passWord = "zhouqiyun";


  private BookDaoImpl bookDaoImpl = new BookDaoImpl();
  @Test
  public void testInsert(){
    //要想其他测试方法能跑起来，都得加上一下两行代码
    Connection connection  = DBUtils.getConnetion(url,userName,passWord);
    ConnectionContext.getInstance().bind(connection);

  String sql = "INSERT INTO trade(userId,tradeTime) VALUES(?,?)";
  long id = bookDaoImpl.insert(sql,1,new Date(new java.util.Date().getTime()));
    System.out.println(id);
  }

  @Test
  public void testUpdate(){
    String sql = "UPDATE book set sales_amount=? where book_id = ?";
    bookDaoImpl.update(sql,10,1);
  }

  @Test
  public void testQuerry(){
    String sql = "SELECT bookId,author,title,price,publishingDate,salesAmount,storeNumber,remark from book where bookId =?";
   Object object  = bookDaoImpl.query(sql,1);
    System.out.println(object);
  }

  @Test
  public void testQuerryForList(){
    String sql = "SELECT bookId,author,title,price,publishingDate,salesAmount,storeNumber,remark from book where bookId < ?";
    List<Book> books = bookDaoImpl.queryForList(sql,4);
    System.out.println(books);

  }

  @Test
  public void testGetSingleVal(){
    String sql = "SELECT count(bookId) from book";
    long count =bookDaoImpl.getSingleVal(sql);
    System.out.println(count);
  }

  @Test
  public void testBatch(){
    String sql = "UPDATE book set salesAmount=?,storeNumber=? where bookId =?";
    bookDaoImpl.batch(sql,new Object[]{20,30,1},new Object[]{2,21,2},new Object[]{15,5,3});

  }
}
