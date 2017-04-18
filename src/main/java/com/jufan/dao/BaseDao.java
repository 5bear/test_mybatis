package com.jufan.dao;


import java.util.List;
import java.util.Map;


public interface BaseDao<T, PK> {
	
	/** 保存*/
	 T save(T entity) ;
	/** 批量保存*/
	 void saveList(List<T> entity);
	/** 更新*/
	 void update(T entity);
	/** 批量更新*/
	 void updateList(List<T> entity);
	/** 通过id删除*/
	 T removeById(PK id);
	/**根据对象删除数据（慎用，危险性比较大） */
	 void removeByObject(T entity);
	/** 通过id查询*/
	 T findById(PK id);
	/** 查询所有*/
	 List<T> findAll();
	/** 根据条件查询列表*/
	 List<T> findList(T entity);
	/** 查询页面显示信息*/
	 PageList findPage(T entity, int currentPage, int pageSize);
	/** 根据MyBatis的配置ID查询*/
	@SuppressWarnings("rawtypes")
	 List<Map> getListBySql(String myBatisSqlId, Object params);
	/** 根据MyBatis的配置ID分页查询*/
	 PageList findPage(String querySqlId, Object conditions, int currentPage, int pageSize);
	/** 根据MyBatis的配置ID更新*/
	 int updateBySql(String myBatisSqlId, Object params);
	/** 按自定义sql查询是否有重复数据*/
	 long findTotalCount(String myBatisSqlId, Object params);
}
