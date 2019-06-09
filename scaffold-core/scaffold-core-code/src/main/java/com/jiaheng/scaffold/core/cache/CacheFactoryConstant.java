package com.jiaheng.scaffold.core.cache;

public class CacheFactoryConstant {

	private CacheFactoryConstant() {
	}
	
	/************************************** 分页排序相关 start **************************************/
	
	/**
	 * 排序属性：返回集合get
	 */
	public static final String SORT_PARAMS_KEY_GET = "get";	
	/**
	 * 排序属性：排序列by
	 */
	public static final String SORT_PARAMS_KEY_BY = "by";
	/**
	 * 排序属性：升降序order
	 */
	public static final String SORT_PARAMS_KEY_ORDER = "order";	
	/**
	 * 排序属性：order:asc
	 */
	public static final String SORT_PARAMS_KEY_ORDER_ASC = "asc";	
	/**
	 * 排序属性：分组key（group）
	 */
	public static final String SORT_PARAMS_KEY_GROUP = "group";
	
	/**
	 * 分页属性：page
	 */
	public static final String PAGE_PARAMS_PAGE = "page";
	
	/**
	 * 分页属性：pageSize
	 */
	public static final String PAGE_PARAMS_PAGE_SIZE = "pageSize";
	
	/**
	 * 默认排序属性名称：defaultSort
	 */
	public static final String DEFAULT_SORT_PARAM_NAME = "defaultSort";	
	/**
	 * 默认排序属性名称：defaultSort
	 */
	public static final String DEFAULT_SORT_REALIZE_PARAM_NAME = "defaultSortRealize";	
	
	
	/************************************** 分页排序相关 end **************************************/
	
	public static final String DEFAULT_NUMBER_VALUE = "0";

	private static final String [] NUMBER_TYPES = {"int", "Integer", "long", "Long", "short", "Short", "float", "Float",	"double","Double", "BigDecimal", "Number", "Date"};

	public static final String [] getNumberTypes(){
		return NUMBER_TYPES;
	}
	
}
