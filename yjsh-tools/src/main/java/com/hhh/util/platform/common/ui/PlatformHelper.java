package com.hhh.util.platform.common.ui;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class PlatformHelper {
	
	//private static final //log //log = //logFactory.get//log(PlatformHelper.class);

	public static final int INSERTCOLUMNS_INDEX = 0;
	public static final int INSERTVALUES_INDEX = 1;
	public static final String START = "start";
	public static final String END = "end";
	public static final String INSERTCOLUMNS = "insertColumns";
	public static final String INSERTVALUES = "insertValues";


	public static HashMap<String, Object> transferKeyToLowerCase(HashMap<String, Object> values) {
		Set<String> fieldNames = values.keySet();
		Iterator<String> itor = fieldNames.iterator();
		HashMap<String, Object> result = new HashMap<String, Object>();
		while (itor.hasNext()) {
			String fieldName = itor.next();
			Object o = values.get(fieldName);
			result.put(fieldName.toLowerCase(), o);
		}
		return result;
	}
	
	public static List transferKeyToLowerCase(List values) {
		int size = values.size();
		List newList = new ArrayList();
		for (int i = 0; i < size; i++) {
			newList.add(transferKeyToLowerCase((HashMap<String, Object>) values.get(i)));
		}
		return newList;

	}
	
	public static String getUUID() {
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}


	/**
	 * 求得当前时间的字符串 格式为"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 */
	public static String getStringOfTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		return time;

	}
	


	public static HashMap<String, Object> filterUnchanged(HashMap<String, Object> oldValues,
			HashMap<String, Object> newValues) {
		if (oldValues == null) {
			return newValues;
		}
		HashMap<String, Object> updated = new HashMap<String, Object>();
		Set<String> fieldNames = newValues.keySet();
		Iterator<String> itor = fieldNames.iterator();
		while (itor.hasNext()) {
			String fieldName = itor.next();
			Object newValue = newValues.get(fieldName);
			if (oldValues.containsKey(fieldName)) {
				Object oldValue = oldValues.get(fieldName);
				if ((newValue == null && oldValue != null)
						|| (newValue != null && oldValue == null)) {
					updated.put(fieldName, newValue);
				}
				if (newValue != null && !newValue.equals(oldValue)) {
					updated.put(fieldName, newValue);
				}
			} else {
				if (newValue != null && newValue.toString().length() > 0) {
					updated.put(fieldName, newValue);
				}
			}
		}
		return updated;
	}

	/**
	 * 取得指定年月日的java.util.Date 转换出错返回 null;
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static java.util.Date getUtilDate(int year, int month, int day, int hourOfDay,
			int minute, int second) {
		Calendar calender = Calendar.getInstance();
		calender.clear();
		calender.set(year, month, day, hourOfDay, minute, second);
		java.util.Date date = calender.getTime();
		return date;
	}

	/**
	 * 取得指定年月日的java.sql.date 转换出错返回 null;
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static java.sql.Date getSQLDate(int year, int month, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date = df.parse(year + "-" + month + "-" + day);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			return sqlDate;
		} catch (ParseException e) {
			////log.error("解析日期出错:", e);
			return null;
		}
	}

	/**
	 * 取得指定把指定java.util.Date 转换为的java.sql.Date
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static java.sql.Date toSQLDate(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	/**
	 * 取得指定是日期的Calendar类;
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Calendar getCalendar(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			date = calendar.getTime();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 字符串转换为int类 转换失败返回默认值;
	 * 
	 * @param in
	 * @param defaultInt
	 * @return
	 */
	public static int parseInteger(String in, int defaultInt) {
		try {
			int out = Integer.parseInt(in);
			return out;
		} catch (Exception e) {
			////log.error("解析整数出错:", e);
			return defaultInt;
		}
	}

	/**
	 * 字符串转换为Float类 转换失败返回默认值;
	 * 
	 * @param in
	 * @param defaultInt
	 * @return
	 */
	public static float parseFloat(String in, float defaultFloat) {
		try {
			float out = Float.parseFloat(in);
			return out;
		} catch (Exception e) {
			////log.error("解析浮点数出错:", e);
			return defaultFloat;
		}
	}

	/**
	 * 把Obj转化为String 如果Obj==null 返回"";
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}

	/**
	 * 把浮点数，小数位数进行指定长度截取(0<size<=4)
	 * 
	 * @param size
	 * @param inputData
	 * @return
	 */
	public static float getFloat(int size, float inputData) {
		if (size <= 0)
			return inputData;
		StringBuilder patternBuilder = new StringBuilder();
		patternBuilder.append(".");
		for (int i = 0; i < size; i++) {
			patternBuilder.append("0");
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(patternBuilder.toString());
		return Float.parseFloat(df.format(inputData));
	}

	/**
	 * 把DoubleString 转换为Double 转换失败时使用默认值
	 * 
	 * @param in
	 * @param defaltValue
	 * @return
	 */
	public static Double parseDouble(String in, Double defaltValue) {
		try {
			Double out = Double.parseDouble(in);
			return out;
		} catch (Exception e) {
			//log.error("解析双精度数出错:", e);
			return defaltValue;
		}
	}
	
	
	/**
	 * 校验日期格式并转换
	 * @param date
	 * @return
	 */
	public static String checkDateFormat(String date) {
		SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		String dateValue = null;
		if(date != null && !"".equals(date.trim())){
			date = date.replace("/", "-");
			date = date.replace("//", "-");
			date = date.replace("\\", "-");
			date = date.replace("年", "-");
			date = date.replace("月", "-");
			date = date.replace("日", "");
			try{
				df1.parse(date);
				dateValue = date;
			}catch(Exception e){
				try{
					df2.parse(date);
					dateValue = date.trim();
				}catch(Exception e1){
					try{
						df3.parse(date);
						dateValue = date.trim();
					}catch(Exception e2){
						//log.error(df1.format(new Date())+"提示：时间："+date+" 格式错误");
					}
				}
			}
		}
		
		return dateValue;
	}

	
	
	
	/**
	 * 校验日期格式并转换
	 * @param date
	 * @return
	 */
	public static Date stringToDateFormat(String date) {
		Date dateValue = null;
		if(date != null && date.length() > 0) {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			
			if(date != null && !"".equals(date.trim())){
				date = date.replace("/", "-");
				date = date.replace("//", "-");
				date = date.replace("\\", "-");
				date = date.replace("年", "-");
				date = date.replace("月", "-");
				date = date.replace("日", "");
				try{
					dateValue = df1.parse(date);
				}catch(Exception e){
					try{
						dateValue = df2.parse(date);
					}catch(Exception e1){
						//log.error(df1.format(new Date())+"提示：时间："+date+" 格式错误");
					}
				}
			}
		}
		
		return dateValue;
	}
	
	
	/**
	 * 校验是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if(value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 内容为空的转为空字符串
	 * @param value
	 * @return
	 */
	public static String changeEmptyValue(String value) {
		if(value == null || value.length() == 0) {
			return " ";
		}
		return value;
	}
	
	
	/**
	 * 将POJO对象转成Map
	 */
	public static HashMap<String, Object> changeModelToMap(Object obj) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			Class c = obj.getClass();
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("get") == 0) {
					if(m[i].invoke(obj, new Object[0]) != null && m[i].invoke(obj, new Object[0]).toString().length() > 0) {
						hashMap.put(m[i].getName().replace("get", "").toLowerCase(), m[i].invoke(obj, new Object[0]));
					}
				}
			}
		} catch (Throwable e) {
			//log.error("将POJO对象转成Map错误" + e.getMessage(),e);
			throw new Exception("将POJO对象转成Map错误" + e.getMessage(),e);
		}
		return hashMap;
	}
	
	
	/**
	 * 获取参数的列信息 拼接成包含列信息的字符串，供insert语句的${insertcolumns}调用 获取参数的值信息
	 * 拼接成所有新增信息的字符串，供insert语句的${insertvalues}调用 对于每个新增的值，如果是数字，则不做处理
	 * 如果是字符串，则值外层加单引号'' 如果是表示日期时间的值，则针对不同的数据库要做相应的处理
	 * 对于oracle，需要用to_timestamp(date[,fmt])函数转换，其中date表示需要转换的日期，fmt表示转换的格式
	 * 如"2011-05-12 17:15:30"
	 * 要转成"to_timestamp('2011-05-12 17:15:30','yyyy-mm-dd hh24:mi:ss')"
	 * 
	 * @param bindedValues
	 *            根据绑定界面得到的新增数据，包含绑定关系(fieldName,value),
	 *            其中fieldName又包含前缀名和字段名(binding_prefix.columnName)
	 * @param binding_prefix
	 *            绑定前缀 null代表所有的前缀,""(长度为0的字符串)代表没有指定前缀的
	 * 
	 * @return String[]{insertcolumns,insertvalues} insertcolumns
	 *         新增数据对应的列名所拼接成的字符串，中间用","隔开 如column1,column2,... insertvalues
	 *         新增数据的值所拼接成的字符串，中间用","隔开
	 *         如'value1',value2,to_timestamp('value3','yyyy-mm-dd
	 *         hh24:mi:ss'),...
	 */
	public static String[] buildInsertColumnsAndValues(HashMap<String, Object> bindedValues,
			String binding_prefix) {
		String insertColumns = "";
		String insertValues = "";

		HashMap<String, HashMap<String, Object>> bindedValuesGroupByTable = getBindedValuesGroupByTable(bindedValues);
		if (binding_prefix == null) {
			Iterator<Entry<String, HashMap<String, Object>>> itor = bindedValuesGroupByTable
					.entrySet().iterator();

			// 定义一个存放列名的集合，用来判断是否有重复的列
			Set<String> fieldNames = new HashSet<String>();

			while (itor.hasNext()) {
				Entry<String, HashMap<String, Object>> fieldGroupByTable = itor.next();
				Iterator<Entry<String, Object>> itor2 = fieldGroupByTable.getValue().entrySet()
						.iterator();
				while (itor2.hasNext()) {
					Entry<String, Object> fieldNameAndValue = itor2.next();
					String insertFieldName = fieldNameAndValue.getKey();
					if (fieldNames.contains(insertFieldName)) {
						continue;
					}
					fieldNames.add(insertFieldName);
					insertColumns += insertFieldName;
					// 中间用","隔开
					insertColumns += ",";

					Object value = fieldNameAndValue.getValue();
					String valueStr = toSqlString(value);
					insertValues += valueStr;
					// 中间用","隔开
					insertValues += ",";
				}
			}
		} else {
			HashMap<String, Object> insertMap = bindedValuesGroupByTable.get(binding_prefix);

			if (insertMap != null) {
				Iterator<Entry<String, Object>> itor = insertMap.entrySet().iterator();
				while (itor.hasNext()) {
					Entry<String, Object> fieldNameAndValue = itor.next();
					String fieldName = fieldNameAndValue.getKey().toString();

					insertColumns += fieldName;
					// 中间用","隔开
					insertColumns += ",";

					Object value = fieldNameAndValue.getValue();
					String valueStr = toSqlString(value);
					insertValues += valueStr;
					// 中间用","隔开
					insertValues += ",";

				}
			}
		}

		// 去掉首尾的','
		insertColumns = removeInvalidSplitTag(insertColumns);
		insertValues = removeInvalidSplitTag(insertValues);

		String[] insertColumnsAndValues = new String[2];
		insertColumnsAndValues[INSERTCOLUMNS_INDEX] = insertColumns;
		insertColumnsAndValues[INSERTVALUES_INDEX] = insertValues;
		return insertColumnsAndValues;
	}
	
	
	/**
	 * 此方法在dao中使用,在操作数据库之前，将键值按表名分组
	 * 
	 * @param values
	 *            包含表名和字段名的绑定关系(tableName.fieldName,value)
	 * @return HashMap<String, HashMap<String,
	 *         Object>,已经按照表名分组的绑定关系(tableName,(fieldName
	 *         ,value))，没有表名的字段，缺省的表名为""
	 */
	public static HashMap<String, HashMap<String, Object>> getBindedValuesGroupByTable(
			HashMap<String, Object> values) {
		HashMap<String, HashMap<String, Object>> bindedValuesGroupByTable = new LinkedHashMap<String, HashMap<String, Object>>();
		Set<String> fieldNamesWithTable = values.keySet();
		Iterator<String> itors = fieldNamesWithTable.iterator();
		while (itors.hasNext()) {
			String fieldNameWithTable = itors.next();
			Object value = values.get(fieldNameWithTable);

			String tableName = "";
			String fieldName = "";
			int index = fieldNameWithTable.indexOf(".");
			if (index != -1) {
				tableName = fieldNameWithTable.substring(0, index);
				fieldName = fieldNameWithTable.substring(index + 1);
			} else {
				tableName = "";
				fieldName = fieldNameWithTable;
			}

			HashMap<String, Object> fields2values = bindedValuesGroupByTable.get(tableName);
			if (fields2values == null) {
				fields2values = new HashMap<String, Object>();
				fields2values.put(fieldName, value);
				bindedValuesGroupByTable.put(tableName, fields2values);
			} else {
				fields2values.put(fieldName, value);
			}
		}
		return bindedValuesGroupByTable;
	}
	
	
	
	/**
	 * 如果拼接后得到的字符串是以","开头，则去掉开头的"," 如果以","结尾，则去掉最后一个","
	 * 
	 * @param jointString
	 * @return
	 */
	private static String removeInvalidSplitTag(String jointString) {
		// 去掉最前面一个","
		if (jointString.indexOf(',') == 0) {
			jointString = jointString.substring(1);
		}
		// 去掉最后一个","
		if (jointString.lastIndexOf(',') > 0
				&& jointString.lastIndexOf(',') == jointString.length() - 1) {
			jointString = jointString.substring(0, jointString.length() - 1);
		}
		return jointString;
	}

	/**
	 * 根据value的类型转成sql语句所需的字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String toSqlString(Object value) {
		String valueStr = "";
		if (value instanceof Integer) {
			valueStr = value.toString();
		} else if (value instanceof String) {
			value = ((String) value).replace("'", "''");
			valueStr = "'" + value + "'";
		} else if (value instanceof Date) {
			Date date = (Date) value;
			String time = getStringOfTime(date);
			valueStr = "'" + time + "'";
		} else {
			valueStr = "'" + value + "'";
		}
		return valueStr;
	}
	
	
}
