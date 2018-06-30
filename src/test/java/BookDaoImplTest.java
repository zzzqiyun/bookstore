import dao.BookDaoImpl;
import dao.interfacedao.BookDao;
import domain.Book;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import web.BookStoreWebUtils;
import web.CriteriaBook;
import web.Page;

/**
 * @author qiyunzhou
 * @date 2018/6/18
 * @time 15:06
 */
public class BookDaoImplTest {
  private BookDao bookDao = new BookDaoImpl();

  @Test
  public void testGetBook() {
    Book book = bookDao.getBook(3);
    System.out.println(book);
  }

  @Test
  public void testGetPage() {
    CriteriaBook criteriaBook = new CriteriaBook(0,60,2);
    Page<Book> page = bookDao.getPage(criteriaBook);
    System.out.println("pageNo:"+page.getPageNo());
    System.out.println("TotalPageNumber:"+page.getTotalPageNumber());
    System.out.println("list:"+page.getList());
    System.out.println("nextPage:"+page.getNextPage());
    System.out.println("prePage:"+page.getPrePage());
  }

  @Test
  public void testGetStoreNumber() {
    int storeNumber = bookDao.getStoreNumber(5);
    System.out.println(storeNumber);
  }


  @Test
  public void testBatchUpdateStoreNumberAndSalesAmount(){
    Collection<ShoppingCartItem> scis = new ArrayList<>();

    Book book = bookDao.getBook(1);
    ShoppingCartItem sci = new ShoppingCartItem(book);
    sci.setQuantity(10);
    scis.add(sci);

    book = bookDao.getBook(2);
    sci = new ShoppingCartItem(book);
    sci.setQuantity(11);
    scis.add(sci);

    book = bookDao.getBook(3);
    sci = new ShoppingCartItem(book);
    sci.setQuantity(12);
    scis.add(sci);

    bookDao.batchUpdateStoreNumberAndSalesAmount(scis);
  }
}
