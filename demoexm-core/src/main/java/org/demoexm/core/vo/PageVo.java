package org.demoexm.core.vo;

import java.io.Serializable;

public class PageVo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5792624405046680509L;
	/**
	 * 总记录条数
	 */
	private int count;
	/**
	 * 页大小
	 */
	private int pageSize;
	/**
	 * 当前页
	 */
	private int pageCount;
	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 排序
	 */
	private String orderBy;
	
	/**
	 * 显示多少个页码，默认为5个
	 */
	private int showPageNumer = 5;

	public PageVo(){
		
	}
	
	public PageVo(int pageSize, int pageCount) {
		this.pageSize = pageSize;
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	/**计算总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		int tempInt = count%pageSize;
		totalPages = count/pageSize;
		if (tempInt != 0){
			totalPages = totalPages + 1;
		}
		return totalPages;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getShowPageNumer() {
		return showPageNumer;
	}

	public void setShowPageNumer(int showPageNumer) {
		this.showPageNumer = showPageNumer;
	}

}
