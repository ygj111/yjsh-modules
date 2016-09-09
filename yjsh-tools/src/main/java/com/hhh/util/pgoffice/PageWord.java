package com.hhh.util.pgoffice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.Table;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;

public  class PageWord {
	
	/**
	 * 填充指定Word文件中的书签位置的表格数据
	 * @param doc word操作对象
	 * @param bookmark word中的书签
	 * @param tableIndex 表格在word文件中的序号，start with 1。
	 * @param startRowNum 填充表格的起始行号，start with 1。
	 * @param data 填充的数据（String数组）
	 */
	public static void fillTableData(WordDocument doc, String bookmark, int tableIndex, int startRowNum, ArrayList <String []>data) {
		//打开数据区域
		DataRegion dataRegion = doc.openDataRegion(bookmark);
		//打开table，openTable(index)方法中的index代表Word文档中table位置的索引，从1开始
		Table table = dataRegion.openTable(tableIndex);
		
		String[] rowData = null;
		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				rowData = (String[])data.get(i);
				for (int j = 0; j < rowData.length; j++) {
					table.openCellRC(startRowNum + i, j + 1).setValue(rowData[j]);
				}
				if (i < data.size() -1 )
					table.insertRowAfter(table.openCellRC(startRowNum + i, 1));
			}
		}
	}
	
	/**
	 * 在指定的doc书签位置填充数据（单个数据）
	 * @param doc word操作对象
	 * @param bookmark word中的书签
	 * @param data 填充的数据
	 * @return
	 */
	public static void fillData(WordDocument doc, String bookmark, String data) {
		
		DataRegion dataRegion1 = doc.openDataRegion(bookmark);
		//给数据区域赋值
		dataRegion1.setValue(data);
		
	}
	
	/**
	 * 在指定的word书签位置填充数据（多组数据）
	 * @param doc word操作对象
	 * @param dataSet key为标签，value为填充值
	 * @return
	 */
	public static void fillData(WordDocument doc, HashMap<String, String> dataSet) {
		
		if (dataSet != null && !dataSet.isEmpty()) {
			Set<String> keys = dataSet.keySet();
			Iterator<String> ite = keys.iterator();
			String key = null;
			String data = null;
			while(ite.hasNext()) {
				key = (String)ite.next();
				data = (String)dataSet.get(key);
				doc.openDataRegion(key).setValue(data);
			}
		}
	}
	
	/**
	 * 在DataTag位置填充数据（单个数据）
	 * @param doc word操作对象
	 * @param tag DataTag标记
	 * @param value 填充值
	 */
	public static void fillTagData(WordDocument doc, String tag, String value) {
		doc.openDataTag(tag).setValue(value);
	}
	
	/**
	 * 在DataTag位置填充数据（多个数据）
	 * @param doc doc word操作对象
	 * @param dataSet key为DataTag，value为填充值
	 */
	public static void fillTagData(WordDocument doc, HashMap<String, String> dataSet) {
		if (dataSet != null && !dataSet.isEmpty()) {
			Set<String> keys = dataSet.keySet();
			Iterator<String> ite = keys.iterator();
			String key = null;
			String data = null;
			while(ite.hasNext()) {
				key = (String)ite.next();
				data = (String)dataSet.get(key);
				doc.openDataTag(key).setValue(data);
			}
		}
	}
}
