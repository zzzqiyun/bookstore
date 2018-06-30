import dao.NewDao;
import domain.Book;
import java.util.List;
import org.junit.Test;

/**
 * @author qiyunzhou
 * @date 2018/5/14
 * @time 14:51
 */
public class testNewDao {
  NewDao dao = new NewDao();

  @Test
  public void testUpdate(){
    String sql = "UPDATE book set salesAmount=? where bookId = ?";
    dao.update(sql,34,3);
  }

  @Test
  public void testGetObject() {
    String sql = "select author,title,price,publishingDate,salesAmount,storeNumber,"
        + "remark from book where bookId =?";
  Book book = dao.getObject(Book.class, sql, 2);
    System.out.println(book);
}
//有问题？
@Test
  public void testGetForList(){

  String sql = "select bookId,author,title,price,publishingDate,salesAmount,storeNumber,"
      + "remark from book where bookId<?";
  List<Book> books = dao.getForList(Book.class,sql,3);
  System.out.println(books);
}


@Test
  public void testGetVal(){
    String sql ="SELECT count(*) from book";
    long i = dao.getVal(sql);
    System.out.println(i);
}
}
