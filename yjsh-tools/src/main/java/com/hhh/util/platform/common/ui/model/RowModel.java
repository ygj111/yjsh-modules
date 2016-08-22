package com.hhh.util.platform.common.ui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowModel extends UModel {

	private ColModel parentCol;
	
	private List<ColModel> childCols;

	public RowModel(ColModel parentCol) {
		this.parentCol = parentCol;
		if (this.parentCol != null) {
			this.parentCol.addChildRow(this);
		}
		this.childCols = new ArrayList<ColModel>();
	}

	public void addChildCol(ColModel childCol) {
		this.childCols.add(childCol);
		childCol.setParentRow(this);
	}

	public void removeChildCol(ColModel childCol) {
		this.childCols.remove(childCol);
		childCol.setParentRow(null);
	}

	public List<ColModel> getChildCols() {
		return childCols;
	}

	public ColModel getParentCol() {
		return parentCol;
	}

	protected void setParentCol(ColModel parentCol) {
		this.parentCol = parentCol;
	}

	public Map<String, String> getSubControls(boolean containItself) {
		Map<String, String> subControls = new HashMap<String, String>();
		if (containItself) {
			String binding = this.getBinding();
			if (binding != null && !binding.isEmpty()) {
				String type = this.getType();
				subControls.put(binding, type);
			}
		}
		List<ColModel> childCols = this.getChildCols();
		for (int i = 0; i < childCols.size(); i++) {
			ColModel colModel = childCols.get(i);
			subControls.putAll(colModel.getSubControls(true));
		}
		return subControls;
	}
}
