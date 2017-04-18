package com.jufan.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseModel implements Serializable {

	/** 筛选条件：开始时间 */
	  private String filterStartTime;
	  /** 筛选条件：结束时间 */
	  private String filterEndTime;
	  /** 用于 order by 排序的sql 语句。如：id desc , name */
	  private String orderSql;
	  private Boolean isLikeQuery=true;
	  
	  
	  private int startRow=0;
	  private int endRow=10;

	  public String getFilterStartTime() {
	    if(filterStartTime!=null){
	      return (filterStartTime.trim().length()==0? String.format("%TF", System.currentTimeMillis()):filterStartTime)+" 00:00:00";
	    }
	    return filterStartTime;
	  }

	  public void setFilterStartTime(String filterStartTime) {
	    this.filterStartTime = filterStartTime;
	  }

	  public String getFilterEndTime() {
	    if(filterEndTime!=null){
	      return (filterEndTime.trim().length()==0? String.format("%TF", System.currentTimeMillis()):filterEndTime)+" 23:59:59";
	    }
	    return filterEndTime;
	  }

	  public void setFilterEndTime(String filterEndTime) {
	    this.filterEndTime = filterEndTime;
	  }

	  public String getOrderSql() {
	    return orderSql;
	  }

	  public void setOrderSql(String orderSql) {
	    this.orderSql = orderSql;
	  }

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public Boolean getIsLikeQuery() {
		return isLikeQuery;
	}

	public void setIsLikeQuery(Boolean isLikeQuery) {
		this.isLikeQuery = isLikeQuery;
	}
	  
	  
}
