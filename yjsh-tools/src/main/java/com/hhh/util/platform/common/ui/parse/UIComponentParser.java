package com.hhh.util.platform.common.ui.parse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.logging.logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/*import org.apache.commons.//logging.//log;
import org.apache.commons.//logging.//logFactory;*/
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hhh.util.platform.common.ui.PlatformHelper;
import com.hhh.util.platform.common.ui.UIComponent;
import com.hhh.util.platform.common.ui.model.ColModel;
import com.hhh.util.platform.common.ui.model.RowModel;
import com.hhh.util.platform.common.ui.model.UModel;

public class UIComponentParser {
	
	//private static //log //log = //logFactory.get//log(UIComponentParser.class);

	//绑定的字段集合
	private HashMap<String,Object> uiBindingValue = new HashMap<String,Object>();
	
	//需要列表展示，自动生成以及必填的字段
	private HashMap<String,HashMap<String,Object>> specialValue = new HashMap<String,HashMap<String,Object>>();
	
	private List<HashMap<String,Object>> specialValueForRow = new ArrayList<HashMap<String,Object>>();
	
	private List<HashMap<String,Object>> specialValueForCol = new ArrayList<HashMap<String,Object>>();
	
	//当前行数
	private int currentRowNum = 1;
	
	//当前列数
	private int currentColNum = 1;
	
	
	public UIComponent parse(InputStream xmlis) throws Exception {
		ColModel colModel = null;
		RowModel rowModel = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbd = dbf.newDocumentBuilder();
		Document doc = dbd.parse(xmlis);
		Element element = doc.getDocumentElement();
		System.out.println(element.getNodeName()+"111");
		if (element.getNodeName().equals("panel")) {//解析最外面col相当于一个面板
			colModel = new ColModel(null);
			checkAttributeExist(element, "num");
			checkAttributeExist(element, "type");
			checkAttributeExist(element, "importClass");
			System.out.println(element.getNodeName()+"111");
			String type = element.getAttribute("type");
			if (!(type.equals("table") || type.equals("div"))) {
				//log.error("模型文件的根元素的属性type的值必须为table");
				throw new Exception("模型文件的根元素的属性type的值必须为table");
			}
			//将xml文件中的元素节点放入model中，并且判断是否有数据绑定
			copyAttributes(element, colModel);
			
			if (element.getAttribute("defaultValue").length() > 0) {
				colModel.setDefaultSelectValue(element.getAttribute("defaultValue"));
			} else {
				colModel.setDefaultSelectValue("");
			}

			
			if (element.getAttribute("bgColor").length() > 0) {
				colModel.setBgColor(element.getAttribute("bgColor"));
			} else {
				colModel.setBgColor("");
			}
			
			//开始解析row行了
			NodeList nodes = element.getChildNodes();
			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if (node.getNodeName().equals("row")) {
							parseRow(colModel, (Element) node);
						} else {
							//log.error("元素名称" + node + "非法");
							throw new Exception("元素名称" + node + "非法");
						}
					}
				}
			}
		} else if (element.getNodeName().equals("row")) {//最外层不是col,row是最外层
			rowModel = new RowModel(null);
			checkAttributeExist(element, "num");
			checkAttributeExist(element, "type");

			String type = element.getAttribute("type");
			if (!(type.equals("div"))) {
				//log.error("模型文件的根元素的属性type的值必须为table");
				throw new Exception("模型文件的根元素的属性type的值必须为table");
			}

			copyAttributes(element, rowModel);
			
			if (element.getAttribute("defaultValue").length() > 0) {
				rowModel.setDefaultSelectValue(element.getAttribute("defaultValue"));
			} else {
				rowModel.setDefaultSelectValue("");
			}

			NodeList nodes = element.getChildNodes();
			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if (node.getNodeName().equals("col")) {
							parseCol(rowModel, (Element) node);
						} else {
							//log.error("元素名称" + node + "非法");
							throw new Exception("元素名称" + node + "非法");
						}
					}
				}
			}
		} else {
			//log.error("模型文件的根元素名必须为col或者row");
			throw new Exception("模型文件的根元素名必须为col或者row");
		}
		UIComponent uiComponent = new UIComponent(rowModel,colModel);
		uiComponent.setBindedControls(uiBindingValue);
		uiComponent.setSpecialValue(specialValue);
		uiComponent.setSpecialValueForRow(specialValueForRow);
		uiComponent.setSpecialValueForCol(specialValueForCol);
		return uiComponent;
	}

	private void parseRow(ColModel colModel, Element element) throws Exception {
		RowModel rowModel = new RowModel(colModel);

		checkAttributeExist(element, "num");
		checkAttributeExist(element, "type");

		String type = element.getAttribute("type");
		validateType(element, type);
		
		if (!"div".equals(type)) {
			addRowInfo(rowModel);//将行放入specialRow中
			copyAttributes(element, rowModel);//将样式解析，并且将绑定值，放入绑定的map中
		} else {
			copyAttributes(element, rowModel);
		}	
		
		rowModel.setDefaultStyle(colModel.getDefaultStyle());
		rowModel.setNum(element.getAttribute("num"));
		rowModel.setType(element.getAttribute("type"));
		rowModel.setDefaultSelectValue(colModel.getDefaultSelectValue());

		if (element.getAttribute("style").length() > 0) {
			rowModel.setStyle(element.getAttribute("style"));
		} else {
			rowModel.setStyle(rowModel.getDefaultStyle());
		}

		if (element.getAttribute("bgcolor").length() > 0) {
			rowModel.setBgColor(element.getAttribute("bgcolor"));
		} else {
			rowModel.setBgColor("");
		}

		// 处理col子元素
		NodeList nodes = element.getChildNodes();
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					if (node.getNodeName().equals("col")) {
						parseCol(rowModel, (Element) node);
					} else {
						//log.error("元素名称" + node + "非法");
						throw new Exception("元素名称" + node + "非法");
					}
				}
			}
		}

	}
	//父对象，加当前对象
	private void parseCol(RowModel rowModel, Element element) throws Exception {
		ColModel colModel = new ColModel(rowModel);
		//当前列
		System.out.println(element.getAttribute("value"));
		checkAttributeExist(element, "num");
		checkAttributeExist(element, "type");

		String type = element.getAttribute("type");
		validateType(element, type);

		addColInfo(element, colModel);
		copyAttributes(element, colModel);
		colModel.setDefaultStyle(rowModel.getDefaultStyle());
		colModel.setDefaultSelectValue(rowModel.getDefaultSelectValue());

		if (element.getAttribute("style").length() > 0) {
			colModel.setStyle(element.getAttribute("style"));
		} else {
			colModel.setStyle(colModel.getDefaultStyle());
		}
		

		// 处理col字元素
		NodeList nodes = element.getChildNodes();
		if (nodes != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getNodeName().equals("row")) {
						parseRow(colModel, (Element) node);
					} else {
						//log.error("元素名称" + node + "非法");
						throw new Exception("元素名称" + node + "非法");
					}
				}
			}
		}
	}
	
	
	private void copyAttributes(Element element, UModel uModel) {
		getSpecialValue(element);
		NamedNodeMap nnm = element.getAttributes();
		
		for (int i = 0; i < nnm.getLength(); i++) {
			Node node = nnm.item(i);
			System.out.println(node.getNodeValue());
			if("binding".equals(node.getNodeName())) {
				uiBindingValue.put(node.getNodeValue().toLowerCase(), "");
			}
			
			
			uModel.addAttribute(node.getNodeName(), node.getNodeValue());
		}
	}
	
	
	/**
	 * 添加行信息
	 * @param rowModel
	 */
	private void addRowInfo(RowModel rowModel) {
		//行 相当于数据库中一条记录
		HashMap<String,Object> specialValueMap = new HashMap<String,Object>();
		rowModel.settRowID(PlatformHelper.getUUID());
		specialValueMap.put("pk", rowModel.getRowID());
		specialValueMap.put("orderid", currentRowNum + "");
		specialValueMap.put("divid", "tr" + currentRowNum);
		
		specialValueForRow.add(specialValueMap);
		currentRowNum += 1;
	}
	
	
	/**
	 * 添加列信息
	 * @param rowModel
	 * @throws Exception 
	 */
	private void addColInfo(Element element,ColModel colModel) throws Exception {
		
		String rowPK = colModel.getParentRow().getRowID();
		System.out.println(rowPK);
		//如果行PK为空则不增加列信息
		if(rowPK == null || rowPK.length() == 0) {
			return;
		}
		//获取节点信息
		HashMap<String,Object> nodeValue = getNodeInfo(element);
		String value = (String) nodeValue.get("value") == null?"":(String) nodeValue.get("value");
		String isshow = (String) nodeValue.get("isshow")== null?"0":(String) nodeValue.get("isshow");
		String isauto = (String) nodeValue.get("isauto")== null?"0":(String) nodeValue.get("isauto");
		String iswrite = (String) nodeValue.get("iswrite")== null?"0":(String) nodeValue.get("iswrite");
		String linktable = (String) nodeValue.get("linktable")== null?"":(String) nodeValue.get("linktable");
		String linkitem = (String) nodeValue.get("linkitem")== null?"":(String) nodeValue.get("linkitem");
		String linkproject = (String) nodeValue.get("linkproject")== null?"":(String) nodeValue.get("linkproject");
		String binding = (String) nodeValue.get("itemname")== null?"":(String) nodeValue.get("itemname");
		String showorder = (String) nodeValue.get("showorder")== null?"0":(String) nodeValue.get("showorder");
		String singleValue = (String) nodeValue.get("singlevalue")== null?"0":(String) nodeValue.get("singlevalue");
		String type = (String) nodeValue.get("type")== null?"":(String) nodeValue.get("type");
		String itemNote = (String) nodeValue.get("itemnote") == null?"":(String) nodeValue.get("itemnote");
		System.out.println(specialValueForCol.size());
		if(specialValueForCol != null && specialValueForCol.size() > 0) {
			boolean isExisted = false;
			for(HashMap<String,Object> map:specialValueForCol) {
				String showpk = (String) map.get("showpk");
				String itemnote = (String) map.get("itemnote");
				String itemname = (String) map.get("itemname");
				System.out.println(showpk+","+itemnote);
				if(showpk.equals(rowPK)) {
						//特殊处理
						if (singleValue.equals("1")) {
							isExisted = false;
						} else if(singleValue.equals("2")) {
							//linkproject强制清空
							linkproject = "";
						} else {
							if(itemnote == null ||  itemnote.length() == 0) {
								map.put("itemnote", value);
								isExisted = true;
							}
							
							//下拉框标签需要绑定重复字段
							if(type!=null&&!("select_start".equals(type)||"option".equals(type))){
								//存在绑定重复字段
								if(!PlatformHelper.isEmpty(itemname) && itemname.equals(binding)) {
									//logger.get//logger(this.getClass().getName()).info("存在绑定重复字段" + binding);
									throw new Exception("存在绑定重复字段" + binding);
								}
							}
						}
				}
				
			}
			
			if(!isExisted){
				if(binding.length() > 0) {
					HashMap<String,Object> specialValueMap = new HashMap<String,Object>();
					specialValueMap.put("itemnote", PlatformHelper.isEmpty(itemNote)?value:itemNote);
					specialValueMap.put("isshow", isshow);
					specialValueMap.put("isauto", isauto);
					specialValueMap.put("iswrite", iswrite);
					specialValueMap.put("linktable", linktable);
					specialValueMap.put("linkitem", linkitem);
					specialValueMap.put("linkproject", linkproject);
					specialValueMap.put("itemname", binding);
					specialValueMap.put("tdid", "td" + currentColNum);
					specialValueMap.put("showpk", rowPK);
					specialValueMap.put("showorder", showorder);
					
					specialValueForCol.add(specialValueMap);
					
					currentColNum += 1;
				}
			}
		} else {
			if(binding.length() > 0) {
				HashMap<String,Object> specialValueMap = new HashMap<String,Object>();
				specialValueMap.put("itemnote", PlatformHelper.isEmpty(itemNote)?value:itemNote);
				specialValueMap.put("isshow", isshow);
				specialValueMap.put("isauto", isauto);
				specialValueMap.put("iswrite", iswrite);
				specialValueMap.put("linktable", linktable);
				specialValueMap.put("linkitem", linkitem);
				specialValueMap.put("linkproject", linkproject);
				specialValueMap.put("itemname", binding);
				specialValueMap.put("tdid", "td" + currentColNum);
				specialValueMap.put("showpk", rowPK);
				specialValueMap.put("showorder", showorder);
				
				specialValueForCol.add(specialValueMap);
				
				currentColNum += 1;
			}
		}
		
		
	}
	
	
	private HashMap<String,Object> getNodeInfo(Element element) {
		NamedNodeMap nnm = element.getAttributes();
		
		HashMap<String,Object> nodeValue = new HashMap<String,Object>();
			
			String isshow = null;
			String isauto = null;
			String iswrite = null;
			String linkfield = null;
			String linkproject = null;
			String itemnote = null;
			String itemname = null;
			String showorder = null;
			String singleValue = null;
			String type = null;
			
			for (int i = 0; i < nnm.getLength(); i++) {
				Node node = nnm.item(i);
				
				if("isshow".equals(node.getNodeName().toLowerCase())) {
					isshow = node.getNodeValue();
						nodeValue.put("isshow", isshow);
				}
				
				if("isauto".equals(node.getNodeName().toLowerCase())) {
					isauto = node.getNodeValue();
						nodeValue.put("isauto", isauto);
				}
				
				if("iswrite".equals(node.getNodeName().toLowerCase())) {
					iswrite = node.getNodeValue();
						nodeValue.put("iswrite", iswrite);
				}
				
				if("linkfield".equals(node.getNodeName().toLowerCase())) {
					linkfield = node.getNodeValue();
						nodeValue.put("linktable", linkfield.split("\\.")[0]);
						nodeValue.put("linkitem", linkfield.split("\\.")[1]);
				}
				
				if("linkproject".equals(node.getNodeName().toLowerCase())) {
					linkproject = node.getNodeValue();
						nodeValue.put("linkproject", linkproject);
				}
				
				if("itemnote".equals(node.getNodeName().toLowerCase())) {
					itemnote = node.getNodeValue();
						nodeValue.put("itemnote", itemnote);
				}
				
				if("value".equals(node.getNodeName().toLowerCase())) {
					itemnote = node.getNodeValue();
						nodeValue.put("value", itemnote);
				}
				
				if("binding".equals(node.getNodeName().toLowerCase())) {
					
					itemname = node.getNodeValue();
					//原本也切割
						nodeValue.put("itemname", itemname);
				}
				
				if("showorder".equals(node.getNodeName().toLowerCase())) {
					showorder = node.getNodeValue();
						nodeValue.put("showorder", showorder);
				}
				
				if("singlevalue".equals(node.getNodeName().toLowerCase())) {
					singleValue = node.getNodeValue();
					nodeValue.put("singlevalue", singleValue);
				}
				
				if("type".equals(node.getNodeName().toLowerCase())) {
					type = node.getNodeValue();
					nodeValue.put("type", type);
				}
			}
		return nodeValue;
	}
	
	

	/**
	 * 获取特殊值
	 * @param element
	 */
	private void getSpecialValue(Element element) {
		NamedNodeMap nnm = element.getAttributes();
		
		HashMap<String,Object> nodeValue = new HashMap<String,Object>();
		
		Node binding_node = nnm.getNamedItem("binding");
		if(binding_node != null) {
			
			String fieldName = binding_node.getNodeValue();
			
			String isshow = null;
			String isauto = null;
			String iswrite = null;
			String linkfield = null;
			String linkproject = null;
			String itemnote = null;
			
			for (int i = 0; i < nnm.getLength(); i++) {
				Node node = nnm.item(i);
				if("isshow".equals(node.getNodeName().toLowerCase())) {
					isshow = node.getNodeValue();
					nodeValue.put("isshow", isshow);
				}
				
				if("isauto".equals(node.getNodeName().toLowerCase())) {
					isauto = node.getNodeValue();
					nodeValue.put("isauto", isauto);
				}
				
				if("iswrite".equals(node.getNodeName().toLowerCase())) {
					iswrite = node.getNodeValue();
					nodeValue.put("iswrite", iswrite);
				}
				
				if("linkfield".equals(node.getNodeName().toLowerCase())) {
					linkfield = node.getNodeValue();
					nodeValue.put("linktable", linkfield.split("\\.")[0]);
					nodeValue.put("linkitem", linkfield.split("\\.")[1]);
				}
				
				if("linkproject".equals(node.getNodeName().toLowerCase())) {
					linkproject = node.getNodeValue();
					nodeValue.put("linkproject", linkproject);
				}
				
				if("itemnote".equals(node.getNodeName().toLowerCase())) {
					itemnote = node.getNodeValue();
					nodeValue.put("itemnote", itemnote);
				}
				
				
			}
					
			
			if(fieldName != null) {
				//原本用了切割，binding字段使用.
				specialValue.put(fieldName, nodeValue);
			
			}
		}
	}

	private void checkAttributeExist(Element element, String attrName) throws Exception {
		if (element.getAttribute(attrName).length() == 0) {
			//log.error("元素(num=" + element.getAttribute("num") + ")的属性" + attrName + "缺失");
			throw new Exception("元素(num=" + element.getAttribute("num") + ")的属性" + attrName + "缺失");
		}
	}

	/**
	 * 验证节点的type属性是否合法
	 * 
	 * @param element
	 *            xml元素
	 * @param type
	 *            原属的type属性值
	 * @return void
	 * @throws
	 */
	private void validateType(Element element, String type) throws Exception {
		if (!(type.equals("td")|| type.equals("br")|| type.equals("blank")|| type.equals("label")||type.equals("button") || type.equals("h1") || type.equals("h2") || type.equals("h3") || type.equals("h4") || type.equals("h5") || type.equals("h6") || type.equals("table") || type.equals("td_start")  || type.equals("div") || type.equals("option") || type.equals("select_end") || type.equals("select_start") || type.equals("checkbox") || type.equals("radio") || type.equals("a_start")  || type.equals("a_end") || type.equals("select") || type.equals("textfield") || type.equals("td_end") || type.equals("tr") || type.equals("text") || type.equals("img")|| type.equals("textarea") || type.equals("sselect")  || type.equals("table"))) {
			//log.error(("元素(num=" + element.getAttribute("num") + ")的属性type" + "的值非法"));
			throw new Exception(("元素(num=" + element.getAttribute("num") + ")的属性type" + "的值非法"));
		}
		if (type.equals("td") || type.equals("text") || type.equals("img")|| type.equals("textarea")) {
			if (element.getChildNodes() != null) {
				for (int i = 0; i < element.getChildNodes().getLength(); i++) {
					Node node = element.getChildNodes().item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						//log.error(("元素(num=" + element.getAttribute("num") + ")的属性type" + "的值非法"));
						throw new Exception(("元素(num=" + element.getAttribute("num") + ")的属性type" + "的值非法"));
					}
				}
			}
		}
	}
}