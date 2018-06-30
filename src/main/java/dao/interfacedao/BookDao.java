package dao.interfacedao;

import domain.Book;
import web.CriteriaBook;
import domain.ShoppingCartItem;
import java.util.Collection;
import java.util.List;
import web.Page;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:31
 */
public interface BookDao {

  /**
   * 根据id获得指定的book对象
   * @param id
   * @return
   */
public abstract Book getBook(Integer id);

/**
 * 根据传入的crteraBook对象返回对应的page对象
 * @param cb
 * @return
 */
public abstract Page<Book> getPage(CriteriaBook cb);

/**
 * 根据传入的criteriaBook对象返回其对应的记录数
 *@param cb
 * @return
 */
public abstract long getTotalBookNumber(CriteriaBook cb);

  /**
   * 根据传入的CirterBook和pageSize返回当前页对应的List
   * @param cb
   * @param PageSize
   * @return
   */
  public List<Book> getPageList(CriteriaBook cb,int PageSize);

  /**
   * 返回指定id的book的storeNumber字段的值
   * @param id
   * @return
   */
  public abstract int getStoreNumber(Integer bookId);

  /**
   * 根据传入的ShoppingCartIte的集合
   * 批量更新book表的storeNumber和saleNumber字段的值
   * @param items
   */
  public abstract void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
