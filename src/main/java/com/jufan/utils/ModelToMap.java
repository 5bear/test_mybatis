package com.jufan.utils;


import com.jufan.model.BaseModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes","unchecked"})
public class ModelToMap {
//	private  final static Logger logger = LoggerFactory.getLogger("LoginLogUtil");
	public static Map modelToMap(Object obj) {
		Map map = model2Map(obj);
		if (obj instanceof BaseModel) {
			BaseModel bm = (BaseModel) obj;

			try {
				if(bm.getFilterStartTime() != null){
					map.put("filterStartTime", bm.getFilterStartTime());
				}else{
					map.remove("filterStartTime");
				}
				if(bm.getFilterEndTime() != null){
					map.put("filterEndTime", bm.getFilterEndTime());
				}else{
					map.remove("filterEndTime");
				}
			} catch (Exception e) {
			}
			try {
				if (bm.getOrderSql() != null)
					map.put("orderSql", bm.getOrderSql());
				if(bm.getIsLikeQuery() !=null)
					map.put("isLikeQuery", bm.getIsLikeQuery());
			} catch (Exception e) {
			}
		}

		return map;
	}

	private static Map model2Map(Object obj) {
		Map hashMap = new HashMap();
		Class c = obj.getClass();
		Field f[] = c.getDeclaredFields();
		Method method;
		String fieldName;
		for (Field field : f) {
			fieldName = field.getName();
			try {
				method = c.getDeclaredMethod("get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1));
				hashMap.put(fieldName, method.invoke(obj));
			} catch (Throwable e) {
				System.err.println(e);
			}
		}
		return hashMap;
	}
}
