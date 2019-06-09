/*
 * 文件名：ExcelUtils.java
 * 版权：
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改内容：
 */
package com.jiaheng.scaffold.common.util.excel;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ExcelUtils {

	/**
	 * JavaBean转Map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!StringUtils.equals(name, "class")) {
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	/**
	 * 创建普通表头
	 * 
	 * @param list 表头名称列表
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(List<String> list) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (String title : list) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建普通表头
	 * 
	 * @param titls 表头名称数组
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (String title : titls) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(title);
			tc.setGrouped(true);
//			if(title.contains("(元)")){
//				tc.setColumnType(TableColumn.COLUMN_TYPE_FLOAT_2);
//			}
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建普通表头
	 * 
	 * @param titls 表头名称数组
	 * @param spanCount 需要行合并的列数。 由第一列数据开始到指定列依次从左到右进行合并操作。 如该值大于表头名称数组，则为全列合并
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] titls, int spanCount) {
		if (spanCount > titls.length)
			spanCount = titls.length;
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		for (int i = 0; i < titls.length; i++) {
			TableColumn tc = new TableColumn();
			tc.setDisplay(titls[i]);
			if (i < spanCount)
				tc.setGrouped(true);
			headMeta.addColumn(tc);
		}
		return headMeta;
	}

	/**
	 * 创建合并表头
	 * 
	 * @param parents 父表头数组
	 * @param children 子表头数组
	 * @return
	 */
	public static TableHeaderMetaData createTableHeader(String[] parents, String[][] children) {
		TableHeaderMetaData headMeta = new TableHeaderMetaData();
		TableColumn parentColumn = null;
		TableColumn sonColumn = null;
		for (int i = 0; i < parents.length; i++) {
			parentColumn = new TableColumn();
			parentColumn.setDisplay(parents[i]);
			if (children != null && children[i] != null) {
				for (int j = 0; j < children[i].length; j++) {
					sonColumn = new TableColumn();
					sonColumn.setDisplay(children[i][j]);
					parentColumn.addChild(sonColumn);
				}
			}
			headMeta.addColumn(parentColumn);
		}
		return headMeta;
	}

	/**
	 * 拼装数据
	 * 
	 * @param list 数据集
	 * @param headMeta 表头
	 * @param fields 对象或Map属性数组（注意：顺序要与表头标题顺序对应，如数据集为List<Object[]>，则该参数可以为null）
	 * @return TableData
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableData createTableData(List list, TableHeaderMetaData headMeta, String[] fields,Map convertorMap) {

		TableData td = new TableData(headMeta);
		TableDataRow row = null;
		if (list != null && list.size() > 0) {
			if (list.get(0).getClass().isArray()) {// 数组类型
				for (Object obj : list) {
					row = new TableDataRow(td);
					for (Object o : (Object[]) obj) {
						row.addCell(o);
					}
					td.addRow(row);
				}
			} else {// JavaBean或Map类型
				for (Object obj : list) {
					row = new TableDataRow(td);
					Map<String, Object> map = (obj instanceof Map) ? (Map<String, Object>) obj : beanToMap(obj);
					for (String key : fields) {
						if(convertorMap == null){
							row.addCell(map.get(key));
							continue;
						}

						Map nidMap = (Map)convertorMap.get(key);
						if(nidMap == null){
							row.addCell(map.get(key));
							continue;
						}
						row.addCell(nidMap.get(map.get(key)+""));
					}
					td.addRow(row);
				}
			}
		}
		return td;
	}

	public static TableData createTableData(List list, TableHeaderMetaData headMeta, String[] fields) {
		return createTableData(list,headMeta,fields,null);
	}
}
