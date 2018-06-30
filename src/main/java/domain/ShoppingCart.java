package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 *
 * @author qiyunzhou
 * @date 2018/6/20
 * @time 20:12
 */
public class ShoppingCart {

  private Map<Integer, ShoppingCartItem> books = new HashMap<>();

  public void addBook(Book book) {
    //1、检查购物车中是否有该件商品，若有，商品加一；若没有，把该商品加入否购物车中
    ShoppingCartItem sci = books.get(book.getBookId());
    if (sci == null) {
      sci = new ShoppingCartItem(book);
      books.put(book.getBookId(), sci);
    } else {
      sci.increment();
    }
  }

  public Boolean hasBook(Integer bookId) {
    return books.containsKey(bookId);
  }

  public Map<Integer, ShoppingCartItem> getBooks() {
    return books;
  }

  /**
   * 获取购物车中的所有的shoppintCartItem组成的集合
   */
  public Collection<ShoppingCartItem> getItems() {
    return  books.values();
  }

  /**
   * 返回购物车中商品的总数量
   */
  public Integer getBookNumber() {
    int total = 0;

    for (ShoppingCartItem sci : books.values()) {
      total += sci.getQuantity();
    }
    return total;
  }

  public float getTotalMoney() {
    float totalMoney = 0;
    for (ShoppingCartItem sci : books.values()) {
      totalMoney += sci.getItemMoney();
    }
    return totalMoney;
  }

  public Boolean isEmpty() {
    return books.isEmpty();
  }

  public void clear() {
    books.clear();
  }

  public void removeItem(int bookId) {
    books.remove(bookId);
  }

  //修改指定购物项的数量
  public void updateItemQuantity(int bookId, int quantity) {
    ShoppingCartItem sci = books.get(bookId);
    if (sci != null) {
      sci.setQuantity(quantity);
    }
  }

}
