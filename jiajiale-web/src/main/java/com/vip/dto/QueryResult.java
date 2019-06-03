package com.vip.dto;
/**
 * 公共查询结果DTO
 * @author Administrator
 *
 */

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QueryResult<T> {
	//总记录数
	private int totalRows;
	//当前页码
	private int pageNo;
	//每页大小
	private int pageSize;
	//总页数
	private int totalPages=-1;
	//当前页记录
	private 	List<T> rows;
	
	public QueryResult() {
		
	}
	
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = (int)totalRows;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getTotalPages() {
		if(totalPages==-1) {
			totalPages=totalRows/pageSize;
			if(totalRows%pageSize!=0) {
				totalPages++;
			}
		}
		return totalPages;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public <X>QueryResult<X> rowsMap(Function<T, X> mapper){
		QueryResult<X> qr=new QueryResult<>();
		qr.setPageNo(this.getPageNo());
		qr.setPageSize(this.getPageSize());
		qr.setTotalRows(this.getTotalRows());
		if(this.rows!=null) {
			qr.setRows(this.rows.stream().map(mapper).collect(Collectors.toList()));	
		}
		return qr;
	}
	
	@Override
	public String toString() {
		return "QueryResult [totalRows=" + totalRows + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalPages="
				+ totalPages + ", rows=" + rows + "]";
	}
	
	
	
	
}
