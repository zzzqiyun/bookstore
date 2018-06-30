package web;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 08:36
 */
public class CriteriaBook {
  private float minPrice = 0;
  private float maxPrice = Integer.MAX_VALUE;

  private int pageNo;

  public CriteriaBook(float minPrice,float maxPrice,int pageNo){
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.pageNo = pageNo;
  }

  public float getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(float minPrice) {
    this.minPrice = minPrice;
  }

  public float getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(float maxPrice) {
    this.maxPrice = maxPrice;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  @Override
  public String toString() {
    return "CriteriaBook{" +
        "minPrice=" + minPrice +
        ", maxPrice=" + maxPrice +
        ", pageNo=" + pageNo +
        '}';
  }
}
