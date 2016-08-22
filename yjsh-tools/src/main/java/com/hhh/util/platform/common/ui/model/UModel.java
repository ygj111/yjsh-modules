package com.hhh.util.platform.common.ui.model;

import java.util.HashMap;

public class UModel {

	private HashMap<String, Object> attributes = new HashMap<String, Object>();

	public void addAttribute(String name, Object value) {
		this.attributes.put(name, value);
	}

	public void removeAttribute(String name) {
		this.attributes.remove(name);
	}

	public Object getAttribute(String name) {
		return this.attributes.get(name);
	}

	public String getNum() {
		return (String) this.attributes.get("num");
	}

	public void setNum(String num) {
		this.attributes.put("num", num);
	}

	public String getType() {
		return (String) this.attributes.get("type");
	}

	public void setType(String type) {
		this.attributes.put("type", type);
	}

	public String getBR() {
		return (String) this.attributes.get("br");
	}

	public void setBR(String br) {
		this.attributes.put("br", br);
	}
	
	public String getSUB() {
		return (String) this.attributes.get("sub");
	}

	public void setSUB(String sub) {
		this.attributes.put("sub", sub);
	}
	
	public String getSUP() {
		return (String) this.attributes.get("sup");
	}

	public void setSUP(String sup) {
		this.attributes.put("sup", sup);
	}

	public String getHeight() {
		return (String) this.attributes.get("height");
	}

	public void setHeight(String height) {
		this.attributes.put("height", height);
	}
	
	public String getDefaultSelectValue() {
		return (String) this.attributes.get("defaultValue");
	}

	public void setDefaultSelectValue(String defaultValue) {
		this.attributes.put("defaultValue", defaultValue);
	}
	
	public String getBorder() {
		return (String) this.attributes.get("border");
	}

	public void setBorder(String border) {
		this.attributes.put("border", border);
	}
	
	public String getDataOptions() {
		return (String) this.attributes.get("data_options");
	}

	public void setDataOptions(String dataOptions) {
		this.attributes.put("data_options", dataOptions);
	}
	
	//单元边沿与其内容之间的空白
	public String getCellpadding() {
		return (String) this.attributes.get("cellpadding");
	}

	public void setCellpadding(String cellpadding) {
		this.attributes.put("cellpadding", cellpadding);
	}
	
	//单元格之间的空白
	public String getCellspacing() {
		return (String) this.attributes.get("cellspacing");
	}

	public void setCellspacing(String cellspacing) {
		this.attributes.put("cellspacing", cellspacing);
	}
	
	//边框显示
	public String getFrame() {
		return (String) this.attributes.get("frame");
	}

	public void setFrame(String frame) {
		this.attributes.put("frame", frame);
	}
	
	//线条显示
	public String getRules() {
		return (String) this.attributes.get("rules");
	}

	public void setRules(String rules) {
		this.attributes.put("rules", rules);
	}

	//宽度
	public String getWidth() {
		return (String) this.attributes.get("width");
	}

	public void setWidth(String width) {
		this.attributes.put("width", width);
	}
	
	
	//对其方式
	public String getTableAlign() {
		return (String) this.attributes.get("align");
	}

	public void setTableAlign(String align) {
		this.attributes.put("align", align);
	}
	

	public void setBinding(String binding) {
		this.attributes.put("binding", binding);
	}

	public String getBinding() {
		return (String) this.attributes.get("binding");
	}


	public void setBgColor(String bgColor) {
		this.attributes.put("bgcolor", bgColor);
	}

	public String getBgColor() {
		return (String) this.attributes.get("bgcolor");
	}

	public void setValue(String value) {
		this.attributes.put("value", value);
	}

	public String getValue() {
		return (String) this.attributes.get("value");
	}

	public void setStyle(String style) {
		this.attributes.put("style", style);
	}

	public String getStyle() {
		return (String) this.attributes.get("style");
	}

	public void setDefaultStyle(String defaultStyle) {
		this.attributes.put("defaultStyle", defaultStyle);
	}

	public String getDefaultStyle() {
		return (String) this.attributes.get("defaultStyle");
	}


	public boolean getEnable() {
		String enable = (String) this.attributes.get("enable");
		if (enable != null && enable.equals("false")) {
			return false;
		} else {
			return true;
		}
	}

	public String getWidgetName() {
		return (String) this.attributes.get("class");
	}

	public void setWidgetName(String widgetName) {
		this.attributes.put("class", widgetName);
	}

	public String getId() {
		return (String) this.attributes.get("id");
	}

	public void setId(String id) {
		this.attributes.put("id", id);
	}

	public String getAlign() {
		return (String) this.attributes.get("align"); 
	}

	public void setAlign(String align) {
		this.attributes.put("align", align);
	}

	public String getValign() {
		return (String) this.attributes.get("valign"); 
	}

	public void setValign(String valign) {
		this.attributes.put("valign", valign);
	}
	
	public String getName() {
		return (String) this.attributes.get("name");
	}

	public void setName(String name) {
		this.attributes.put("name", name);
	}

	public String getSize() {
		return (String) this.attributes.get("size");
	}

	public void setSize(String size) {
		this.attributes.put("size", size);
	}

	public String getSrc() {
		return (String) this.attributes.get("src");
	}

	public void setSrc(String src) {
		this.attributes.put("src", src);
	}

	public String getOnclick() {
		return (String) this.attributes.get("onclick");
	}

	public void setOnclick(String onclick) {
		this.attributes.put("onclick", onclick);
	}
	
	public String getTitle() {
		return (String) this.getAttribute("title");
	}
	
	public void setTitle(String title){
		
		this.addAttribute("title", title);
	}
	
	public String getDateformat() {
		return (String) this.getAttribute("dateformat");
	}

	public void setDateformat(String dateformat) {
		this.addAttribute("dateformat", dateformat);
	}
	
	//行PK
	public String getRowID() {
		return (String) this.attributes.get("rowid");
	}

	public void settRowID(String rowid) {
		this.attributes.put("rowid", rowid);
	}
	
	public void setImportClass(String importClass) {
		this.attributes.put("importClass", importClass);
	}

	public String getImportClass() {
		return (String) this.attributes.get("importClass");
	}

}
