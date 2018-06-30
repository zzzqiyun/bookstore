package dao;

import com.sun.xml.internal.rngom.digested.DDataPattern.Param;
import dao.interfacedao.BookDao;
import domain.Book;
import domain.ShoppingCartItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import web.CriteriaBook;
import web.Page;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 14:51
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

  @Override
  public Book getBook(Integer id) {
    String sql = "SELECT bookId,author,title,price,publishingDate,salesAmount,storeNumber,remark from book where bookId =?";
    return (Book)query(sql,id);
  }

  @Override
  public Page<Book> getPage(CriteriaBook cb){
    Page<Book> page = new Page<>(cb.getPageNo());
    page.setPageSize(3);
    page.setTotalItemNumber(getTotalBookNumber(cb));
    cb.setPageNo(page.getPageNo());
    page.setList(getPageList(cb,page.getPageSize()));
    return page;
  }

  //1
  @Override
  public long getTotalBookNumber(CriteriaBook cb) {
    String sql = "SELECT count(bookId) from book"
        + " where price >=? and price <= ?";
    return  getSingleVal(sql,cb.getMinPrice(),cb.getMaxPrice());
  }

  //3
  @Override
  public List<Book> getPageList(CriteriaBook cb, int PageSize) {

    String sql = "SELECT bookId,author,title,price,publishingDate,salesAmount,storeNumber,remark from book"
        + " where price >=? and price <= ? limit ?,?";
    return  queryForList(sql,cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*PageSize,PageSize);
  }

   //2
   // mysql limit限制查询 fromIndex 从0开始
  @Override
  public int getStoreNumber(Integer id) {
    String sql = "SELECT storeNumber from book where bookId =?";
   return  getSingleVal(sql,id);
  }

  @Override
  public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
  String sql = "update bookstore.book set storeNumber= storeNumber-?,salesAmount = salesAmount+? where bookId = ?";
    Object[][] params = null;
    params = new Object[items.size()][3];
    List<ShoppingCartItem> items1 = new ArrayList<>(items);
    for(int i =0;i<items.size();i++){
      params[i][0]=items1.get(i).getQuantity();
      params[i][1]=items1.get(i).getQuantity();
      params[i][2]=items1.get(i).getBook().getBookId();
    }
  batch(sql,params);
  }
}
