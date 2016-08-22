package com.hhh.util.platform.common.ui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ColModel extends UModel {

	private RowModel parentRow;
	
	private List<RowModel> childRows;

	public ColModel(RowModel parentRow) {
			this.parentRow = parentRow;
			if (this.parentRow != null) {
				this.parentRow.addChildCol(this);
			}
		
		this.childRows = new ArrayList<RowModel>();
	}
	

	public RowModel getParentRow() {
		return parentRow;
	}

	protected void setParentRow(RowModel parentRow) {
		this.parentRow = parentRow;
	}
	

	public void addChildRow(RowModel childRow) {
		this.childRows.add(childRow);
		childRow.setParentCol(this);
	}

	public void removeChildRow(RowModel childRow) {
		this.childRows.remove(childRow);
		childRow.setParentCol(null);
	}

	public List<RowModel> getChildRows() {
		return childRows;
	}
	
	public String getIsShow() {
		return (String) this.getAttribute("isshow");
	}

	public void setIsShow(String isshow) {
		this.addAttribute("isshow", isshow);
	}
	
	public String getIsWrite() {
		return (String) this.getAttribute("iswrite");
	}

	public void setIsWrite(String isWrite) {
		this.addAttribute("iswrite", isWrite);
	}
	
	public String getColspan() {
		return (String) this.getAttribute("colspan");
	}

	public void setColspan(String colspan) {
		this.addAttribute("colspan", colspan);
	}
	
	public String getIsAuto() {
		return (String) this.getAttribute("isauto");
	}

	public void setIsAuto(String isauto) {
		this.addAttribute("isauto", isauto);
	}
	
	public String getSelectList() {
		return (String) this.getAttribute("list");
	}

	public void setSelectList(String selectList) {
		this.addAttribute("list", selectList);
	}
	
	public String getHeaderValue() {
		return (String) this.getAttribute("headerValue");
	}

	public void setHeaderValue(String headerValue) {
		this.addAttribute("headerValue", headerValue);
	}
	
	public String getNowrap() {
		return (String) this.getAttribute("nowrap");
	}

	public void setNowrap(String nowrap) {
		this.addAttribute("nowrap", nowrap);
	}
	
	public String getRowspan() {
		return (String) this.getAttribute("rowspan");
	}

	public void setRowspan(String rowspan) {
		this.addAttribute("rowspan", rowspan);
	}
	
	public String getRows() {
		return (String) this.getAttribute("rows");
	}

	public void setRows(String rows) {
		this.addAttribute("rows", rows);
	}
	
	public String getCols() {
		return (String) this.getAttribute("cols");
	}

	public void setCols(String cols) {
		this.addAttribute("cols", cols);
	}
	
	
	public String getAccesskey() {
		return (String) this.getAttribute("accesskey");
	}

	public void setAccesskey(String accesskey) {
		this.addAttribute("accesskey", accesskey);
	}
	
	public String getTabindex() {
		return (String) this.getAttribute("tabindex");
	}

	public void setTabindex(String tabindex) {
		this.addAttribute("tabindex", tabindex);
	}
	
	public String getOnchange() {
		return (String) this.getAttribute("onchange");
	}

	public void setOnchange(String onchange) {
		this.addAttribute("onchange", onchange);
	}
	
	public String getOnsubmit() {
		return (String) this.getAttribute("onsubmit");
	}

	public void setOnsubmit(String onsubmit) {
		this.addAttribute("onsubmit", onsubmit);
	}
	
	public String getOnreset() {
		return (String) this.getAttribute("onreset");
	}

	public void setOnreset(String onreset) {
		this.addAttribute("onreset", onreset);
	}
	
	public String getOnselect() {
		return (String) this.getAttribute("onselect");
	}

	public void setOnselect(String onselect) {
		this.addAttribute("onselect", onselect);
	}
	
	public String getOnblur() {
		return (String) this.getAttribute("onblur");
	}

	public void setOnblur(String onblur) {
		this.addAttribute("onblur", onblur);
	}
	
	public String getOnfocus() {
		return (String) this.getAttribute("onfocus");
	}

	public void setOnfocus(String onfocus) {
		this.addAttribute("onfocus", onfocus);
	}
	
	public String getOnclick() {
		return (String) this.getAttribute("onclick");
	}

	public void setOnclick(String onclick) {
		this.addAttribute("onclick", onclick);
	}
	
	public String getOndblclick() {
		return (String) this.getAttribute("ondblclick");
	}

	public void setOndblclick(String ondblclick) {
		this.addAttribute("ondblclick", ondblclick);
	}
	
	public String getOnmousedown() {
		return (String) this.getAttribute("onmousedown");
	}

	public void setOnmousedown(String onmousedown) {
		this.addAttribute("onmousedown", onmousedown);
	}
	
	public String getOnmousemove() {
		return (String) this.getAttribute("onmousemove");
	}

	public void setOnmousemove(String onmousemove) {
		this.addAttribute("onmousemove", onmousemove);
	}
	
	public String getOnmouseout() {
		return (String) this.getAttribute("onmouseout");
	}

	public void setOnmouseout(String onmouseout) {
		this.addAttribute("onmouseout", onmouseout);
	}
	
	public String getOnmouseover() {
		return (String) this.getAttribute("onmouseover");
	}

	public void setOnmouseover(String onmouseover) {
		this.addAttribute("onmouseover", onmouseover);
	}
	
	public String getOnmouseup() {
		return (String) this.getAttribute("onmouseup");
	}

	public void setOnmouseup(String onmouseup) {
		this.addAttribute("onmouseup", onmouseup);
	}
	
	public String getOnkeydown() {
		return (String) this.getAttribute("onkeydown");
	}

	public void setOnkeydown(String onkeydown) {
		this.addAttribute("onkeydown", onkeydown);
	}
	
	public String getOnkeypress() {
		return (String) this.getAttribute("onkeypress");
	}

	public void setOnkeypress(String onkeypress) {
		this.addAttribute("onkeypress", onkeypress);
	}
	
	public String getOnkeyup() {
		return (String) this.getAttribute("onkeyup");
	}

	public void setOnkeyup(String onkeyup) {
		this.addAttribute("onkeyup", onkeyup);
	}
	
	public String getBinding() {
		return (String) this.getAttribute("binding");
	}

	public void setBinding(String binding) {
		this.addAttribute("binding", binding);
	}
	
	public String getDisabled() {
		return (String) this.getAttribute("disabled");
	}

	public void setDisabled(String disabled) {
		this.addAttribute("disabled", disabled);
	}
	
	public String getLabel() {
		return (String) this.getAttribute("label");
	}

	public void setLabel(String label) {
		this.addAttribute("label", label);
	}
	
	public String getSelected() {
		return (String) this.getAttribute("selected");
	}

	public void setSelected(String selected) {
		this.addAttribute("selected", selected);
	}
	
	public String getStatus() {
		return (String) this.getAttribute("status");
	}

	public void setStatus(String status) {
		this.addAttribute("status", status);
	}
	
	public String getTextField() {
		return (String) this.getAttribute("textfield");
	}

	public void setTextField(String textfield) {
		this.addAttribute("textfield", textfield);
	}
	
	public String getReadonly() {
		return (String) this.getAttribute("readonly");
	}

	public void setReadonly(String readonly) {
		this.addAttribute("readonly", readonly);
	}
	
	public String getHref() {
		return (String) this.getAttribute("href");
	}

	public void setHref(String href) {
		this.addAttribute("href", href);
	}
	
	public String getOnChange() {
		return (String) this.getAttribute("onchange");
	}

	public void setOnChange(String onchange) {
		this.addAttribute("onchange", onchange);
	}
	
	public String getAlt() {
		return (String) this.getAttribute("alt");
	}

	public void setAlt(String alt) {
		this.addAttribute("alt", alt);
	}
	
	public String getItemNote() {
		return (String) this.getAttribute("itemNote");
	}

	public void setItemNote(String itemnote) {
		this.addAttribute("itemNote", itemnote);
	}
	
	public String getPosition() {
		return (String) this.getAttribute("position");
	}

	public void setPosition(String position) {
		this.addAttribute("position", position);
	}
	
	public String getLinkField() {
		return (String) this.getAttribute("linkfield");
	}

	public void setLinkField(String linkfield) {
		this.addAttribute("linkfield", linkfield);
	}
	
	public String getLinkProject() {
		return (String) this.getAttribute("linkproject");
	}

	public void setLinkProject(String linkproject) {
		this.addAttribute("linkproject", linkproject);
	}
	

	public Map<String, String> getSubControls(boolean containItself) {
		Map<String, String> subControls = new HashMap<String, String>();
		if (containItself) {
			String binding = this.getBinding();
			if (binding != null && !binding.isEmpty()) {
				String type = this.getType();
				subControls.put(binding,type);
			}
		}
		List<RowModel> childRows = this.getChildRows();
		for (int i = 0; i < childRows.size(); i++) {
			RowModel rowModel = childRows.get(i);
			subControls.putAll(rowModel.getSubControls(true));
		}
		return subControls;
	}
	
	public void setImportClass(String importClass) {
		this.addAttribute("importClass", importClass);
	}

	public String getImportClass() {
		return (String) this.getAttribute("importClass");
	}
}
