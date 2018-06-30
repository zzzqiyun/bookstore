package domain;

/**
 * 购物车中的商品，包含来商品的应用以及购物车中商品的数量
 *
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 09:20
 */
public class ShoppingCartItem {

  private Book book;
  private int quantity;

  public ShoppingCartItem(Book book) {
    this.book = book;
    this.quantity = 1;
  }

  public Book getBook() {
    return book;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * 返回该商品在购物车的钱数
   */
  public float getItemMoney() {
    return book.getPrice() * quantity;
  }

  public void increment() {
    quantity++;
  }
}
