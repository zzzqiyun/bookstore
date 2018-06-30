package web;

import static java.lang.Math.toIntExact;

import java.util.List;

/**
 * @author qiyunzhou
 * @date 2018/5/10
 * @time 09:24
 */
public class Page<T> {

  //当前页
  private int pageNo;

  //总记录数
  private long totalItemNumber;

  //当前页的List
  private List<T> list;

  //每页记录数
  private int pageSize ;

  public Page(int pageNo) {
    super();
    this.pageNo = pageNo;
  }

  //校验页数是否合法
  public int getPageNo() {
    if(pageNo<0){
      pageNo=1;
    }
    if(pageNo>getTotalPageNumber()){
        pageNo=getTotalPageNumber();
    }
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public long getTotalItemNumber() {
    return totalItemNumber;
  }

  public void setTotalItemNumber(long totalItemNumber) {
    this.totalItemNumber = totalItemNumber;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  //获取总页数
  public int getTotalPageNumber(){

    int totalPageNumber = (int)(totalItemNumber/pageSize);
    if((totalItemNumber%pageSize)!=0){
      return ++totalPageNumber;
    }
    return totalPageNumber;
  }

  public boolean isHasNext(){
    if(pageNo<getTotalPageNumber()){
      return true;
    }
    return false;
  }

  public boolean isHasPrev(){
    if(pageNo>1){
      return true;
    }
    return false;
  }

  public int getPrePage(){
    if(isHasPrev()){
      return pageNo-1;
    }
    return pageNo;
  }

  public int getNextPage(){
    if(isHasNext()){
      return pageNo+1;
    }
    return pageNo;
  }
}
