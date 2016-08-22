package com.hhh.util.platform.common.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hhh.util.platform.common.ui.model.ColModel;
import com.hhh.util.platform.common.ui.model.RowModel;
import com.hhh.util.platform.common.ui.model.UModel;

public class UIComponent {
	

	private ColModel rootModel = null;
	
	private RowModel rowModel = null;
	
	//数据库绑定字段和值
	private HashMap<String,Object> bindedControls = new HashMap<String,Object>();
	
	//列表中展示的字段
	private HashMap<String,HashMap<String,Object>> specialValue = new HashMap<String,HashMap<String,Object>>();
	
	private List<HashMap<String,Object>> specialValueForRow = new ArrayList<HashMap<String,Object>>();
	
	private List<HashMap<String,Object>> specialValueForCol = new ArrayList<HashMap<String,Object>>();

	public ColModel getRootModel() {
		return rootModel;
	}

	public UIComponent(RowModel rowModel,ColModel rootModel) {
		this.rootModel = rootModel;
		this.rowModel = rowModel;
	}
	
	
	/**
	 * 组装HTML
	 * @param builder
	 * @throws Exception
	 */
	public String toHTML(StringBuilder builder) throws Exception {
		if(rootModel != null) {
			//DIV
			if ("div".equals(rootModel.getType())) {
				parserDIV(rootModel, builder);
				createRowControl(rootModel, builder,false);
				builder.append("</div>\n");
			//TABLE
			} else if ("table".equals(rootModel.getType())) {
				parserTable(rootModel,builder);
				createRowControl(rootModel, builder,false);
				builder.append("</table>\n");
			} 
		} else if(rowModel != null) {
			if (rowModel.getType().equals("div")) {
				parserDIV(rowModel, builder);
				createColControl(rowModel, builder,false);
				builder.append("</div>\n");
			}
		}
		
		//处理为空的情况
		if(builder == null || builder.toString().length() == 0) {
			builder = new StringBuilder();
			builder.append("<table></table>\n");
		}
		return builder.toString();
	}

	
	
	/**
	 * 组装HTML(显示绑定字段)
	 * @param builder
	 * @throws Exception
	 */
	public String toBindingHTML(StringBuilder builder) throws Exception {
		if(rootModel != null) {
			//DIV
			if ("div".equals(rootModel.getType())) {
				parserDIV(rootModel, builder);
				createRowControl(rootModel, builder,true);
				builder.append("</div>\n");
			//TABLE
			} else if ("table".equals(rootModel.getType())) {
				parserTable(rootModel,builder);
				createRowControl(rootModel, builder,true);
				builder.append("</table>\n");
			} 
		} else if(rowModel != null) {
			if (rowModel.getType().equals("div")) {
				parserDIV(rowModel, builder);
				createColControl(rowModel, builder,true);
				builder.append("</div>\n");
			}
		}
		
		//处理为空的情况
		if(builder == null || builder.toString().length() == 0) {
			builder = new StringBuilder();
			builder.append("<table></table>\n");
		}
		return builder.toString();
	}

	
	
	/**
	 * 增加行
	 * @param colParentModel
	 * @param builder
	 * @throws Exception
	 */
	private void createRowControl(ColModel colParentModel,StringBuilder builder,boolean isBinding) throws Exception {
		List<RowModel> rows = colParentModel.getChildRows();
		for (int i = 0; i < rows.size(); i++) {
			RowModel row = rows.get(i);

			String type = row.getType();
			if (type.equals("tr")) {
				parserTr(row,builder);
				if (row.getChildCols().size() > 0) {
					this.createColControl(row, builder,isBinding);
				}
				builder.append("</tr>\n");
			} else if (type.equals("div")) {
				parserDIV(row, builder);
				if (row.getChildCols().size() > 0) {
					this.createColControl(row, builder,isBinding);
				}
				builder.append("</div>\n");
			} 
		}
	}

	
	
	/**
	 * 增加列
	 * @param rowParentModel
	 * @param builder
	 * @throws Exception
	 */
	private void createColControl(RowModel rowParentModel,StringBuilder builder,boolean isBinding) throws Exception {
		List<ColModel> cols = rowParentModel.getChildCols();

		for (int i = 0; i < cols.size(); i++) {
			ColModel col = cols.get(i);
			String col_type = col.getType();
			
			//TD
			if (col_type.equals("td") || col_type.equals("td_start")) {
				parserTD(col,builder);
			//文本框、日期等
			} else if (col_type.equals("text")) {
				parserText(col,builder,isBinding);
				//按钮
			} else if (col_type.equals("button")) {
				parserButton(col,builder);
				//多选框
			} else if (col_type.equals("checkbox")) {
				parserCheckBox(col,builder,isBinding);
				//单选框
			} else if (col_type.equals("radio")) {
				parserRadio(col,builder,isBinding);
			} else if (col_type.equals("a_start")) {
				parserLinkA(col,builder);
			} else if (col_type.equals("a_end")) {
				builder.append("</a>\n");
			//下拉框
			} else if (col_type.equals("select_start")) {
				parserSelect(col,builder);
			//下拉框选项
			} else if (col_type.equals("iterator_start")) {
				parserIterator(col,builder);
			} else if (col_type.equals("iterator_end")) {
				builder.append("</s:iterator>\n");
			//下拉框选项(固定值)
			} else if (col_type.equals("option")) {
				parserOption(col,builder,isBinding);
			} else if (col_type.equals("select_end")) {
				builder.append("</select>\n");
			//Struts2标签下拉框
			}else if (col_type.equals("sselect")) {
				parserSSelect(col,builder);
				builder.append("</s:select>\n");
			//图片
			} else if (col_type.equals("img")) {
				parserImg(col,builder,isBinding);
			//多行文本框
			} else if (col_type.equals("textarea")) {
				parserTextArea(col,builder,isBinding);
			//LABEL
			} else if (col_type.equals("label")) {
				parserLabel(col,builder);
			//空格
			} else if (col_type.equals("blank")) {
				builder.append("&nbsp;");
			//换行
			} else if (col_type.equals("br")) {
				builder.append("&nbsp;");
			//标题
			} else if(col_type.equals("h1") || col_type.equals("h2")
					|| col_type.equals("h3") || col_type.equals("h4")
					|| col_type.equals("h5") || col_type.equals("h6")) {
				parserTitle(col,builder);
			}else if (col_type.equals("td_end")) {
				builder.append("</td>\n");
			} else if (col_type.equals("table")) {
				parserTable(col,builder);
				createRowControl(col, builder,isBinding);
				builder.append("</table>\n");

			} else if (col_type.equals("tr")) {
				parserTr(col,builder);
				if (col.getChildRows().size() > 0) {
					this.createColControl(col, builder,isBinding);
				}
				builder.append("</tr>\n");
			} else if (col_type.equals("div")) {
				parserDIV(col, builder);
				if (col.getChildRows().size() > 0) {
					this.createColControl(col, builder,isBinding);
				}
				builder.append("</div>\n");
			}
		}
	}
	
	
	/**
	 * 增加列
	 * @param rowParentModel
	 * @param builder
	 * @throws Exception
	 */
	private void createColControl(ColModel col,StringBuilder builder,boolean isBinding) throws Exception {

			String col_type = col.getType();
			
			//TD
			if (col_type.equals("td") || col_type.equals("td_start")) {
				parserTD(col,builder);
			//文本框、日期等
			} else if (col_type.equals("text")) {
				parserText(col,builder,isBinding);
				//按钮
			} else if (col_type.equals("button")) {
				parserButton(col,builder);
				//多选框
			} else if (col_type.equals("checkbox")) {
				parserCheckBox(col,builder,isBinding);
				//单选框
			} else if (col_type.equals("radio")) {
				parserRadio(col,builder,isBinding);
			} else if (col_type.equals("a_start")) {
				parserLinkA(col,builder);
			} else if (col_type.equals("a_end")) {
				builder.append("</a>\n");
			//下拉框
			} else if (col_type.equals("select_start")) {
				parserSelect(col,builder);
			//下拉框选项
			} else if (col_type.equals("iterator_start")) {
				parserIterator(col,builder);
			} else if (col_type.equals("iterator_end")) {
				builder.append("</s:iterator>\n");
				//下拉框选项(固定值)
			} else if (col_type.equals("option")) {
				parserOption(col,builder,isBinding);
			} else if (col_type.equals("select_end")) {
				builder.append("</select>\n");
			//图片
			} else if (col_type.equals("img")) {
				parserImg(col,builder,isBinding);
				//Struts2标签下拉框
			}else if (col_type.equals("sselect")) {
					parserSSelect(col,builder);
					builder.append("</s:select>\n");
			//多行文本框
			} else if (col_type.equals("textarea")) {
				parserTextArea(col,builder,isBinding);
			//LABEL
			} else if (col_type.equals("label")) {
				parserLabel(col,builder);
			//空格
			} else if (col_type.equals("blank")) {
				builder.append("&nbsp;");
			} else if (col_type.equals("br")) {
				builder.append("&nbsp;");
			//标题
			} else if(col_type.equals("h1") || col_type.equals("h2")
					|| col_type.equals("h3") || col_type.equals("h4")
					|| col_type.equals("h5") || col_type.equals("h6")) {
				parserTitle(col,builder);
			}else if (col_type.equals("td_end")) {
				builder.append("</td>\n");
			} else if (col_type.equals("table")) {
				parserTable(col,builder);
				createRowControl(col, builder,isBinding);
				builder.append("</table>\n");

			} else if (col_type.equals("tr")) {
				parserTr(col,builder);
				if (col.getChildRows().size() > 0) {
					this.createColControl(col, builder,isBinding);
				}
				builder.append("</tr>\n");
			} else if (col_type.equals("div")) {
				parserDIV(col, builder);
				if (col.getChildRows().size() > 0) {
					this.createColControl(col, builder,isBinding);
				}
				builder.append("</div>\n");
			} 
	}
	
	
	
	
	/**
	 * 将上下标、空格、换行、颜色、时间标识解析为html格式
	 * @param value
	 * @return
	 */
	private String replaceAppointContent(String value) {
		if(value.contains("$SUB")){
			value = value.replace("$SUB", "<sub>");
			value = value.replace("$", "</sub>");
		}
		if(value.contains("$SUP")){ 
			value = value.replace("$SUP", "<sup>");
			value = value.replace("$", "</sup>");
		}
		if(value.contains("$BLANK")){ 
			value = value.replace("$BLANK", "&nbsp;");
		} 
		if(value.contains("$BR")){ 
			value = value.replace("$BR", "<br />");
		}
		if(value.contains("$RED")){ 
			value = value.replace("$RED", "<span style='color:red'>");
			value = value.replace("$", "</span>");
		}
		if(value.contains("$YELLOW")){ 
			value = value.replace("$YELLOW", "<span style='color:yellow'>");
			value = value.replace("$", "</span>");
		}
		if(value.contains("$BLUE")){ 
			value = value.replace("$BLUE", "<span style='color:blue'>");
			value = value.replace("$", "</span>");
		}
		if(value.contains("$GREEN")){ 
			value = value.replace("$GREEN", "<span style='color:green'>");
			value = value.replace("$", "</span>");
		}
		if(value.contains("$CURRENTDATE")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			value = value.replace("$CURRENTDATE", sdf.format(new Date()).toString());
		}
		return value;
	}
	
	
	/**
	 * 将DIV解析为html格式
	 * @param value
	 * @return
	 */
	private void parserDIV(UModel uModel,StringBuilder builder) {
		String root_id = uModel.getId();
		
		String root_class = uModel.getWidgetName();
		
		String root_style = uModel.getStyle();
		
		builder.append("<div");
		if (root_class != null && root_class.length() > 0) {
			builder.append(" class=\""+ root_class +"\"");
		}
		if (root_id != null && root_id.length() > 0) {
			builder.append(" id=\""+ root_id +"\"");
		}
		if (root_style != null && root_style.length() > 0) {
			builder.append(" style=\""+ root_style +"\"");
		}
		builder.append(">\n");
	}
	
	/**
	 * 将TABLE解析为html格式
	 * @param value
	 * @return
	 */
	private void parserTable(UModel uModel,StringBuilder builder) {
		//表格宽
		String table_width = uModel.getWidth();  
		//表格高
		String table_height = uModel.getHeight();
		//表格边框
		String table_border = uModel.getBorder();
		//背景颜色
		String table_bgcolor = uModel.getBgColor();
		//单元边沿与其内容之间的空白
		String table_cellpadding = uModel.getCellpadding();
		//单元格之间的空白
		String table_cellspacing = uModel.getCellspacing();
		//边框显示
		String table_frame = uModel.getFrame();
		//线条显示
		String table_rules = uModel.getRules();
		//表格对齐方式
		String table_align = uModel.getTableAlign();
		//CSS样式名称
		String table_class = uModel.getWidgetName();
		//样式
		String table_style = uModel.getStyle();
		String importClass = uModel.getImportClass();
		builder.append("<table importClass=\""+importClass+"\"");
		if (table_class != null && table_class.length() > 0) {
			builder.append(" class=\""+ table_class +"\"");
		}
		if (table_width != null && table_width.length() > 0) {
			builder.append(" width=\""+ table_width +"\"");
		}
		if (table_height != null && table_height.length() > 0) {
			builder.append(" height=\""+ table_height +"\"");
		}
		if (table_border != null && table_border.length() > 0) {
			builder.append(" border=\""+ table_border +"\"");
		}
		if (table_bgcolor != null && table_bgcolor.length() > 0) {
			builder.append(" bgcolor=\""+ table_bgcolor +"\"");
		}
		if (table_frame != null && table_frame.length() > 0) {
			builder.append(" frame=\""+ table_frame +"\"");
		}
		if (table_rules != null && table_rules.length() > 0) {
			builder.append(" rules=\""+ table_rules +"\"");
		}
		if (table_align != null && table_align.length() > 0) {
			builder.append(" align=\""+ table_align +"\"");
		}
		if (table_style != null && table_style.length() > 0) {
			builder.append(" style=\""+ table_style +"\"");
		}
		if (table_cellpadding != null && table_cellpadding.length() > 0) {
			builder.append(" cellpadding=\""+ table_cellpadding +"\"");
		}
		if (table_cellspacing != null && table_cellspacing.length() > 0) {
			builder.append(" cellspacing=\""+ table_cellspacing +"\"");
		}
		builder.append(">\n");
	}
	
	
	/**
	 * 将TR解析为html格式
	 * @param value
	 * @return
	 */
	private void parserTr(UModel uModel,StringBuilder builder) {
		//行宽
		String row_width = uModel.getWidth();  
		//行高
		String row_height = uModel.getHeight();
		//行ID
		String table_id = uModel.getId();
		//垂直对齐
		String row_valign = uModel.getValign();
		//左右对齐方式
		String row_align = uModel.getTableAlign();
		//CSS样式名称
		String row_class = uModel.getWidgetName();
		//样式
		String row_style = uModel.getStyle();
		
		builder.append("<tr");
		if (row_class != null && row_class.length() > 0) {
			builder.append(" class=\""+ row_class +"\"");
		}
		if (table_id != null && table_id.length() > 0) {
			builder.append(" id=\""+ table_id +"\"");
		}
		if (row_width != null && row_width.length() > 0) {
			builder.append(" width=\""+ row_width +"\"");
		}
		if (row_height != null && row_height.length() > 0) {
			builder.append(" height=\""+ row_height +"\"");
		}
		if (row_valign != null && row_valign.length() > 0) {
			builder.append(" valign=\""+ row_valign +"\"");
		}
		if (row_align != null && row_align.length() > 0) {
			builder.append(" align=\""+ row_align +"\"");
		}
		if (row_style != null && row_style.length() > 0) {
			builder.append(" style=\""+ row_style +"\"");
		}
		builder.append(">\n");
	}
	
	
	/**
	 * 将TD解析为html格式
	 * @param value
	 * @return
	 */
	private void parserTD(ColModel col,StringBuilder builder) {
		String col_type = col.getType();
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_align = col.getAlign();
		String col_height = col.getHeight();
		String col_width = col.getWidth();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_style = col.getStyle();
		String col_colspan = col.getColspan();
		String col_rowspan = col.getRowspan();
		
		if (col_type.equals("td") || col_type.equals("td_start")) {
			builder.append("<td");
			if (col_class != null && col_class.length() > 0) {
				builder.append(" class=\""+ col_class +"\"");
			}
			if (col_id != null && col_id.length() > 0) {
				builder.append(" id=\""+ col_id +"\"");
			}
			if (col_width != null && col_width.length() > 0) {
				builder.append(" width=\""+ col_width +"\"");
			}
			if (col_height != null && col_height.length() > 0) {
				builder.append(" height=\""+ col_height +"\"");
			}
			if (col_colspan != null && col_colspan.length() > 0) {
				builder.append(" colspan=\""+ col_colspan +"\"");
			}
			if (col_rowspan != null && col_rowspan.length() > 0) {
				builder.append(" rowspan=\""+ col_rowspan +"\"");
			}
			if (col_align != null && col_align.length() > 0) {
				builder.append(" align=\""+ col_align +"\"");
			}
			if (col_style != null && col_style.length() > 0) {
				builder.append(" style=\""+ col_style +"\"");
			}
			
			if(col_type.equals("td")) {
				if (col_value != null && col_value.length() > 0) {
					col_value = replaceAppointContent(col_value);
					builder.append(">"+ col_value);
				}
				builder.append("</td>\n");
			} else if(col_type.equals("td_start")) {
				builder.append(">\n");
			}
		}
	}
	
	
	/**
	 * 将Text解析为html格式
	 * @param value
	 * @return
	 */
	private void parserText(ColModel col,StringBuilder builder,boolean isBinding) {
		String col_type = col.getType();
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_label = col.getLabel() == null ? "" : col.getLabel();
		String col_width = col.getWidth();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_style = col.getStyle();
		String col_onclick = col.getOnclick();
		String col_onblur = col.getOnblur();
		String col_size = col.getSize();
		String col_name = col.getName();
		String col_position = col.getPosition();
		/*String col_isauto = col.getIsAuto();
		String col_iswrite = col.getIsWrite();
		String col_isshow = col.getIsShow();*/
		String col_binding = col.getBinding();
		String col_readonly = col.getReadonly();
		String col_title = col.getTitle();
		String col_dateformat = col.getDateformat();
		
		Object bindingValue = bindedControls.get(col_binding);
		
		StringBuilder builder_temp = new StringBuilder();
		builder_temp.append("<input");
		if (col_class != null && col_class.length() > 0) {
			builder_temp.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder_temp.append(" id=\""+ col_id +"\"");
		}
		if (col_style != null && col_style.length() > 0) {
			builder_temp.append(" style=\""+ col_style +"\"");
		}
		//如果存在绑定字段则用绑定字段内容作为name的内容
		if (col_binding != null && col_binding.length() > 0) {
			builder_temp.append(" name=\""+ col_binding + "\"");
		} else if (col_name != null && col_name.length() > 0) {
			builder_temp.append(" name=\""+ col_name + "\"");
		}
		if (col_size != null && col_size.length() > 0) {
			builder_temp.append(" size=\""+ col_size +"\"");
		}
		if (col_type != null && col_type.length() > 0) {
			builder_temp.append(" type=\""+ col_type +"\"");
		}
		if (col_width != null && col_width.length() > 0) {
			builder_temp.append(" width=\""+ col_width +"\"");
		}
		if (col_title != null && col_title.length() > 0) {
			builder_temp.append(" title=\""+ col_title +"\"");
		}
		if(col_dateformat != null && col_dateformat.length() > 0 && bindingValue instanceof Date){
			SimpleDateFormat sdf = new SimpleDateFormat(col_dateformat);
			bindingValue = sdf.format(bindingValue);
		}
		
		if(isBinding) {
			//如果value内容来自数据表则用数据表内容取代默认值
			if (col_binding != null && col_binding.length() > 0) {
				builder_temp.append(" value=\""+ col_binding + "\"");
			} else if (col_value != null && col_value.length() > 0) {
				col_value = replaceAppointContent(col_value);
				builder_temp.append(" value=\""+ col_value + "\"");
			}
		} else {
			//如果value内容来自数据表则用数据表内容取代默认值
			if (bindingValue != null) {
				builder_temp.append(" value=\""+ bindingValue + "\"");
			} else if (col_value != null && col_value.length() > 0) {
				col_value = replaceAppointContent(col_value);
				builder_temp.append(" value=\""+ col_value + "\"");
			}
		}
		
		if (col_onclick != null && col_onclick.length() > 0) {
			builder_temp.append(" onclick=\""+ col_onclick +"\"");
		}
		
		if (col_onblur != null && col_onblur.length() > 0) {
			builder_temp.append(" onblur=\""+ col_onblur +"\"");
		}
		
		/*//是否自动增长
		if (col_isauto != null && col_isauto.length() > 0) {
			builder_temp.append(" isauto=\""+ col_isauto +"\"");
		}
		//是否必填
		if (col_iswrite != null && col_iswrite.length() > 0) {
			builder_temp.append(" iswrite=\""+ col_iswrite +"\"");
		}
		//是否列表显示
		if (col_isshow != null && col_isshow.length() > 0) {
			builder_temp.append(" isshow=\""+ col_isshow +"\"");
		}*/
				
		//是否只读
		if (col_readonly != null && col_readonly.length() > 0) {
			builder_temp.append(" readonly=\""+ col_readonly +"\"");
		}
		
		
		builder_temp.append("/>");
		
		//label是在前边显示还是后边显示
		if ("before".equals(col_position)) {
			builder.append(col_label);
			builder.append(builder_temp.toString());
		} else if ("after".equals(col_position)) {
			builder.append(builder_temp.toString());
			builder.append(col_label);
		} else {
			builder.append(col_label);
			builder.append(builder_temp.toString());
		}
		builder_temp.append("\n");
	}
	
	
	/**
	 * 将Button解析为html格式
	 * @param value
	 * @return
	 */
	private void parserButton(ColModel col,StringBuilder builder) {
		String col_type = col.getType();
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_label = col.getLabel() == null ? "" : col.getLabel();
		String col_width = col.getWidth();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_style = col.getStyle();
		String col_onclick = col.getOnclick();
		String col_size = col.getSize();
		String col_name = col.getName();
		String col_position = col.getPosition();
		
		StringBuilder builder_temp = new StringBuilder();
		builder_temp.append("<input");
		if (col_class != null && col_class.length() > 0) {
			builder_temp.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder_temp.append(" id=\""+ col_id +"\"");
		}
		if (col_style != null && col_style.length() > 0) {
			builder_temp.append(" style=\""+ col_style +"\"");
		}
		if (col_name != null && col_name.length() > 0) {
			builder_temp.append(" name=\""+ col_name +"\"");
		}
		if (col_size != null && col_size.length() > 0) {
			builder_temp.append(" size=\""+ col_size +"\"");
		}
		if (col_type != null && col_type.length() > 0) {
			builder_temp.append(" type=\""+ col_type +"\"");
		}
		if (col_width != null && col_width.length() > 0) {
			builder_temp.append(" width=\""+ col_width +"\"");
		}
		if (col_onclick != null && col_onclick.length() > 0) {
			builder_temp.append(" onclick=\""+ col_onclick +"\"");
		}
		if (col_value != null && col_value.length() > 0) {
			col_value = replaceAppointContent(col_value);
			builder_temp.append(" value=\""+ col_value + "\"");
		}
		builder_temp.append("/>");
		
		if (col_label != null && col_label.length() > 0) {
			col_label = replaceAppointContent(col_label);
		}
		
		if ("before".equals(col_position)) {
			builder.append(col_label);
			builder.append(builder_temp.toString());
		} else if ("after".equals(col_position)) {
			builder.append(builder_temp.toString());
			builder.append(col_label);
		} else {
			builder.append(col_label);
			builder.append(builder_temp.toString());
		}
		builder_temp.append("\n");
	}
	
	
	
	/**
	 * 将CheckBox解析为html格式
	 * @param value
	 * @return
	 */
	private void parserCheckBox(ColModel col,StringBuilder builder,boolean isBinding) {
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_name = col.getName();
		String col_position = col.getPosition();
		String col_selectValue = col.getDefaultSelectValue();
		String col_binding = col.getBinding();
		String col_onclick = col.getOnclick();
		Object bindingValue = bindedControls.get(col_binding);
		
		StringBuilder builder_temp = new StringBuilder();
		builder_temp.append("<input type=\"checkbox\"");
		if (col_class != null && col_class.length() > 0) {
			builder_temp.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder_temp.append(" id=\""+ col_id +"\"");
		}
		if (col_binding != null && col_binding.length() > 0) {
			builder_temp.append(" name=\""+ col_binding + "\"");
		} else if (col_name != null && col_name.length() > 0) {
			builder_temp.append(" name=\""+ col_name + "\"");
		}
		
		if (col_onclick != null && col_onclick.length() > 0) {
			builder_temp.append(" onclick=\""+ col_onclick +"\"");
		}
		//如果数据库有值则选中本多选框
		if(col_selectValue != null && col_selectValue.length() > 0) {
			if(col_selectValue.equals(bindingValue)) {
				builder_temp.append(" checked=\"true\"");
			}
		}  else if (bindingValue != null && bindingValue.toString().length() > 0) {
			builder_temp.append(" checked=\"true\"");
		}
		
		builder_temp.append("/>");
		
		if (col_value != null && col_value.length() > 0) {
			col_value = replaceAppointContent(col_value);
		}
		
		if(isBinding) {
			if (col_binding != null && col_binding.length() > 0) {
				col_value = col_value + ":"+ col_binding;
			}
		} 
		
		
		
		if ("before".equals(col_position)) {
			builder.append(col_value);
			builder.append(builder_temp.toString());
		} else if ("after".equals(col_position)) {
			builder.append(builder_temp.toString());
			builder.append(col_value);
		} else {
			builder.append(col_value);
			builder.append(builder_temp.toString());
		}
		builder_temp.append("\n");
	}
	
	
	/**
	 * 将Radio解析为html格式
	 * @param value
	 * @return
	 */
	private void parserRadio(ColModel col,StringBuilder builder,boolean isBinding) {
		String col_label = col.getLabel() == null ? "" : col.getLabel();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_name = col.getName();
		String col_position = col.getPosition();
		String col_binding = col.getBinding();
		String col_value = col.getValue();
		Object bindingValue = bindedControls.get(col_binding);
		
		StringBuilder builder_temp = new StringBuilder();
		builder_temp.append("<input type=\"radio\"");
		if (col_class != null && col_class.length() > 0) {
			builder_temp.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder_temp.append(" id=\""+ col_id +"\"");
		}
		if (col_binding != null && col_binding.length() > 0) {
			builder_temp.append(" name=\""+ col_binding + "\"");
		} else if (col_name != null && col_name.length() > 0) {
			builder_temp.append(" name=\""+ col_name + "\"");
		}
		if (col_value != null && col_value.length() > 0) {
			builder_temp.append(" value=\""+ col_value +"\"");
		}
		
		//如果数据库有值则选中本多选框
		if (bindingValue != null && bindingValue.toString().length() > 0) {
			builder_temp.append(" checked=\"true\"");
		}
		
		builder_temp.append("/>");
		
		if (col_label != null && col_label.length() > 0) {
			col_label = replaceAppointContent(col_label);
		}
		
		if(isBinding) {
			if (col_binding != null && col_binding.length() > 0) {
				col_label = col_label + ":"+ col_binding;
			}
		}
		
		if ("before".equals(col_position)) {
			builder.append(col_label);
			builder.append(builder_temp.toString());
		} else if ("after".equals(col_position)) {
			builder.append(builder_temp.toString());
			builder.append(col_label);
		} else {
			builder.append(col_label);
			builder.append(builder_temp.toString());
		}
		builder_temp.append("\n");
		
	}
	
	
	/**
	 * 将Img解析为html格式
	 * @param value
	 * @return
	 */
	private void parserImg(ColModel col,StringBuilder builder,boolean isBinding) {
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_onclick = col.getOnclick();
		String col_file_src = col.getSrc();
		String col_name = col.getName();
		String col_alt = col.getAlt();
		String col_binding = col.getBinding()== null ? "" : col.getBinding();
		
		builder.append("<img");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder.append(" id=\""+ col_id +"\"");
		}
		if (col_binding != null && col_binding.length() > 0) {
			builder.append(" name=\""+ col_binding + "\"");
		} else if (col_name != null && col_name.length() > 0) {
			builder.append(" name=\""+ col_name + "\"");
		}
		if (col_file_src != null && col_file_src.length() > 0) {
			builder.append(" src=\""+ col_file_src +"\"");
		}
		if (col_alt != null && col_alt.length() > 0) {
			builder.append(" alt=\""+ col_alt +"\"");
		}
		if (col_onclick != null && col_onclick.length() > 0) {
			builder.append(" onclick=\""+ col_onclick +"\"");
		}
		
		if(isBinding) {
			if (col_binding != null && col_binding.length() > 0) {
				col_value = col_value + ":"+ col_binding;
			}
		}
		if (col_value != null && col_value.length() > 0) {
			builder.append(" value=\""+ col_value +"\">");
			builder.append(col_value);
			builder.append("\n");
		}else {
			builder.append("/>\n");
		}
	}
	
	
	/**
	 * 将TextArea解析为html格式
	 * @param value
	 * @return
	 */
	private void parserTextArea(ColModel col,StringBuilder builder,boolean isBinding) {
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_name = col.getName();
		String col_style = col.getStyle();
		String col_title = col.getTitle();
		String col_binding = col.getBinding();
		String col_readonly = col.getReadonly();
		Object bindingValue = bindedControls.get(col_binding);
		
		String col_rows = col.getRows();
		String col_cols = col.getCols();
		builder.append("<textarea");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder.append(" id=\""+ col_id +"\"");
		}
		if (col_binding != null && col_binding.length() > 0) {
			builder.append(" name=\""+ col_binding + "\"");
		} else if (col_name != null && col_name.length() > 0) {
			builder.append(" name=\""+ col_name + "\"");
		}
		if (col_rows != null && col_rows.length() > 0) {
			builder.append(" rows=\""+ col_rows +"\"");
		}
		if (col_cols != null && col_cols.length() > 0) {
			builder.append(" cols=\""+ col_cols +"\"");
		}
		if (col_style != null && col_style.length() > 0) {
			builder.append(" style=\""+ col_style +"\"");
		}
		if (col_title != null && col_title.length() > 0) {
			builder.append(" title=\""+ col_title +"\"");
		}
		//是否只读
		if (col_readonly != null && col_readonly.length() > 0) {
			builder.append(" readonly=\""+ col_readonly +"\"");
		}
		
		if(isBinding) {
			if (col_binding != null && col_binding.length() > 0) {
				builder.append(">"+ col_binding + "</textarea>");
			} else {
				builder.append("></textarea>\n");
			}
		} else {
			if (bindingValue != null && bindingValue.toString().length() > 0) {
				builder.append(">"+ bindingValue + "</textarea>");
			} else {
				builder.append("></textarea>\n");
			}
		}
	}
	
	
	/**
	 * 将Option解析为html格式
	 * @param value
	 * @return
	 */
	private void parserOption(ColModel col,StringBuilder builder,boolean isBinding) {
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_label = col.getLabel() == null ? "" : col.getLabel();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_style = col.getStyle();
		String col_name = col.getName();
		String col_disabled = col.getDisabled();
		String col_selected = col.getSelected();
		String col_binding = col.getBinding();
		String col_onclick = col.getOnclick();
		Object bindingValue = bindedControls.get(col_binding);
		
		builder.append("<option");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder.append(" id=\""+ col_id +"\"");
		}
		if (col_binding != null && col_binding.length() > 0) {
			builder.append(" name=\""+ col_binding + "\"");
		} else if (col_name != null && col_name.length() > 0) {
			builder.append(" name=\""+ col_name + "\"");
		}
		if (col_disabled != null && col_disabled.length() > 0) {
			builder.append(" disabled=\""+ col_disabled +"\"");
		}
		if (col_label != null && col_label.length() > 0) {
			builder.append(" label=\""+col_label +"\"");
		}
		if (bindingValue != null && col_value != null && col_value.equals(bindingValue)) {//空值也是一种值
			builder.append(" selected=\"true\"");
		} else if (col_selected != null && col_selected.length() > 0) {
			builder.append(" selected=\""+ col_selected +"\"");
		}
		if (col_style != null && col_style.length() > 0) {
			builder.append(" style=\""+ col_style +"\"");
		}
		if (col_onclick != null && col_onclick.length() > 0) {
			builder.append(" onclick=\""+ col_onclick +"\"");
		}
		if (col_value != null && col_value.length() > 0) {
			col_value = replaceAppointContent(col_value);
		}
		
		if (col_value != null && col_value.length() > 0) {
			if(isBinding) {
				if (col_binding != null && col_binding.length() > 0) {
					builder.append(" value=\""+ col_value + ":"+ col_binding +"\">");
				}
			}
			builder.append(" value=\""+ col_value +"\">");
			builder.append(col_value);
		}else {
			builder.append(" value=\"\">");
			builder.append("--请选择--");
		}
		builder.append("</option>\n");
	}
	
	
	/**
	 * 将Label解析为html格式
	 * @param value
	 * @return
	 */
	private void parserLabel(ColModel col,StringBuilder builder) {
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_class = col.getWidgetName();
		
		builder.append("<label");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		} else {
			builder.append(" class=\"default_label\"");
		}
		
		if (col_value != null && col_value.length() > 0) {
			if (col_value != null && col_value.length() > 0) {
				col_value = replaceAppointContent(col_value);
			}
			builder.append(" value=\""+ col_value +"\">");
			builder.append(col_value + "</label>\n");
		}else {
			builder.append("></label>\n");
		}
	}
	
	/**
	 * 将H1~H6解析为html格式
	 * @param value
	 * @return
	 */
	private void parserTitle(ColModel col,StringBuilder builder) {
		String col_type = col.getType();
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_style = col.getStyle();
		
		builder.append("<" + col_type);
		if (col_style != null && col_style.length() > 0) {
			builder.append(" style=\""+ col_style +"\"");
		}
		
		if (col_value != null && col_value.length() > 0) {
			col_value = replaceAppointContent(col_value);
		}
		if (col_value != null && col_value.length() > 0) {
			builder.append(">" + col_value);
			builder.append("</" + col_type + ">\n");
		} else {
			builder.append("> </" + col_type + ">\n");
		}
	}
	
	
	/**
	 * 将Select解析为html格式
	 * @param value
	 * @return
	 */
	private void parserSelect(ColModel col,StringBuilder builder) {
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_size = col.getSize();
		String col_name = col.getName();
		String data_options = col.getDataOptions();
		String col_onchange = col.getOnchange();
		
		builder.append("<select");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder.append(" id=\""+ col_id +"\"");
		}
		if (col_name != null && col_name.length() > 0) {
			builder.append(" name=\""+ col_name +"\"");
		}
		if (col_size != null && col_size.length() > 0) {
			builder.append(" size=\""+ col_size +"\"");
		}
		if (col_onchange != null && col_onchange.length() > 0) {
			builder.append(" onchange=\""+ col_onchange +"\"");
		}
		if (data_options != null && data_options.length() > 0) {
			builder.append(" data-options=\""+ data_options +"\"");
		}
		builder.append(">\n");
	}
	
	
	/**
	 * 将Select解析为html格式
	 * @param value
	 * @return
	 */
	private void parserSSelect(ColModel col,StringBuilder builder) {
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_name = col.getName();
		String col_selectList = col.getSelectList();
		String col_headerValue = col.getHeaderValue();
		String col_disabled = col.getDisabled();
		
		builder.append("<s:select");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder.append(" id=\""+ col_id +"\"");
		}
		if (col_name != null && col_name.length() > 0) {
			builder.append(" name=\""+ col_name +"\"");
		}
		if (col_selectList != null && col_selectList.length() > 0) {
			builder.append(" list=\""+ col_selectList +"\"");
		}
		if (col_headerValue != null && col_headerValue.length() > 0) {
			builder.append(" headerValue=\""+ col_headerValue +"\"");
		}
		if (col_disabled != null && col_disabled.length() > 0) {
			builder.append(" disabled=\""+ col_disabled +"\"");
		}
		builder.append(">\n");
	}
	
	/**
	 * 将<A>超链接解析为html格式
	 * @param value
	 * @return
	 */
	private void parserLinkA(ColModel col,StringBuilder builder) {
		String col_onclick = col.getOnclick();
		String col_href = col.getHref();
		String col_class = col.getWidgetName();
		
		builder.append("<a");
		if (col_href != null && col_href.length() > 0) {
			builder.append(" href=\""+ col_href +"\"");
		}
		if (col_onclick != null && col_onclick.length() > 0) {
			builder.append(" onclick=\""+ col_onclick +"\"");
		}
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		builder.append(">");
	}
	
	
	/**
	 * 将Iterator解析为html格式
	 * @param value
	 * @return
	 */
	private void parserIterator(ColModel col,StringBuilder builder) {
		String col_value = col.getValue() == null ? "" : col.getValue();
		String col_class = col.getWidgetName();
		String col_id = col.getId();
		String col_onclick = col.getOnclick();
		String col_status = col.getStatus();
		 
		builder.append("<s:iterator");
		if (col_class != null && col_class.length() > 0) {
			builder.append(" class=\""+ col_class +"\"");
		}
		if (col_id != null && col_id.length() > 0) {
			builder.append(" id=\""+ col_id +"\"");
		}
		if (col_onclick != null && col_onclick.length() > 0) {
			builder.append(" onclick=\""+ col_onclick +"\"");
		}
		if (col_status != null && col_status.length() > 0) {
			builder.append(" status=\""+ col_status +"\"");
		}
		if (col_value != null && col_value.length() > 0) {
			builder.append(" value=\""+ col_value +"\">");
			builder.append(col_value);
			builder.append("\n");
		}else {
			builder.append(">\n");
		}
	}
	
	
	
	
	/**
	 * 特换特殊字符或内容
	 * @param builder
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public String replaceSpecialContent(StringBuilder builder,String oldChar,String newChar) {
		return builder.toString().replace(oldChar, newChar);
	}

	public void setBindedControls(HashMap<String, Object> bindedControls) {
		this.bindedControls = bindedControls;
	}

	public HashMap<String, Object> getBindedControls() {
		return bindedControls;
	}

	public HashMap<String,HashMap<String,Object>> getSpecialValue() {
		return specialValue;
	}

	public void setSpecialValue(HashMap<String,HashMap<String,Object>> specialValue) {
		this.specialValue = specialValue;
	}

	public List<HashMap<String, Object>> getSpecialValueForRow() {
		return specialValueForRow;
	}

	public void setSpecialValueForRow(
			List<HashMap<String, Object>> specialValueForRow) {
		this.specialValueForRow = specialValueForRow;
	}

	public List<HashMap<String, Object>> getSpecialValueForCol() {
		return specialValueForCol;
	}

	public void setSpecialValueForCol(
			List<HashMap<String, Object>> specialValueForCol) {
		this.specialValueForCol = specialValueForCol;
	}
	
	
}