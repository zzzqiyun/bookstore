package domain;

import java.util.Set;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:17
 */
public class TradeItem {
  private int itemId;
  private int quantity;
  private int bookId;
  private int tradeId;
  private Book book;

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {

    this.book = book;
  }

  public TradeItem() {
  }

  public TradeItem( int quantity, int bookId, int tradeId) {
    this.quantity = quantity;
    this.bookId = bookId;
    this.tradeId = tradeId;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public int getTradeId() {
    return tradeId;
  }

  public void setTradeId(int tradeId) {
    this.tradeId = tradeId;
  }

  @Override
  public String toString() {
    return "TradeItem{" +
        "itemId=" + itemId +
        ", quantity=" + quantity +
        ", bookId=" + bookId +
        ", tradeId=" + tradeId +
        '}';
  }
}
