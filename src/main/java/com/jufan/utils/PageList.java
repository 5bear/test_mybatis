package com.jufan.utils;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "rawtypes", "serial" })
public class PageList implements java.io.Serializable{
	// 分页结果集
	private List dataList = null;
	// 记录总数
	private int totalcount = 0;
	// 每页显示记录数
	private int pageSize = 0;
	// 当前页数
	private int currentPage = 1;
	// 总页数
	private int totalPageCount = 1;
	
	/*初始化分页组件*/
	public PageList(int totalcount, int pageSize, int currentPage) {
		setTotalcount(totalcount);
		setPageSize(pageSize);
		setCurrentPage(currentPage);
	}
	
	public PageList(int pageSize, int currentPage){
		setPageSize(pageSize);
		setCurrentPage(currentPage);
	}
	
	public void updateIndex(){
		totalPageCount=getTotalPageCount();
		if(currentPage>totalPageCount){
			currentPage=totalPageCount;
		}else if(currentPage<1){
			currentPage=1;
		}
	}

	public List getDataList() {
		return dataList==null?new ArrayList():dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPageSize() {
		return pageSize<=0?10:pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage<=0?1:currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPageCount() {
		int p=0;
		if (totalcount % pageSize == 0) {
			p=totalcount / pageSize;
		} else {
			p=totalcount / pageSize + 1;
		}
		return p==0?1:p;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
}
