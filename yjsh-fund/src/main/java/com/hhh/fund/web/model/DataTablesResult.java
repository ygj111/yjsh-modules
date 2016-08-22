package com.hhh.fund.web.model;

import java.util.List;

/**
 * dataTables分页用到
 * @author 3hygj
 *
 * @param <T>
 */
public class DataTablesResult<T> {
	private int draw;
	private int recordsTotal;//总记录数
	private int recordsFiltered;//分页后的记录数
	private List<T> data;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
