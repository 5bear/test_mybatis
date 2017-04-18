package com.jufan.daoImpl;

import com.jufan.dao.BaseDao;
import com.jufan.model.BaseModel;
import com.jufan.model.TiUserInf;
import com.jufan.utils.ModelToMap;
import com.jufan.utils.PageList;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;


@SuppressWarnings({"unchecked" })
public class BaseDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T, PK> {
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired(required = true)
	@Resource(name = "sqlSessionFactory")
	public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public String getClassName(){
		try{
			return getClass().getSimpleName().replace("DaoImpl", "");
		}catch(Exception e){
			e.printStackTrace();
			return getClass().getSimpleName();
		}
	}
	
	/**
	 * save(保存）
	 * @param entity 类
	 * @return T 
	 * @throws RuntimeException
	 */
	@SuppressWarnings("rawtypes")
	public T save(T entity) {
		try {
			//设置创建和更新时间
//			RimeUtil.setTime(entity, "createTime","updateTime");
//			String model= getModel(entity);
//			Class modelClass= obj.getClass();
			Class modelClass=entity.getClass();
			try {
				Method setTime= modelClass.getDeclaredMethod("setCreateDatetime",Date.class);
				setTime.invoke(entity, new Date());
				Method setUpdateTime= modelClass.getDeclaredMethod("setLastUpdateDatetime",Date.class);
				setUpdateTime.invoke(entity, new Date());
			} catch (Exception e) {
				// TODO 
				System.out.println("!!!没有自动插入的字段");
			}
			try {
//				TiUserInf user= (TiUserInf)ServletActionContext.getRequest().getSession().getAttribute("user");
				ServletRequestAttributes st=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
//				ServletWebRequest st1=((ServletWebRequest)RequestContextHolder.getRequestAttributes());
				HttpServletRequest request=st.getRequest();
				TiUserInf user= (TiUserInf)request.getSession().getAttribute("user");
				Method setUser= modelClass.getDeclaredMethod("setCreateOperatorId",String.class);
				setUser.invoke(entity, user.getId());
				Method setUpdateUser= modelClass.getDeclaredMethod("setLastUpdateOperatorId",String.class);
				setUpdateUser.invoke(entity, user.getId());
				Method setUserName= modelClass.getDeclaredMethod("setCreateOperatorName",String.class);
				setUserName.invoke(entity, user.getUserName());
				Method setupUserName= modelClass.getDeclaredMethod("setLastUpdateOperatorName",String.class);
				setupUserName.invoke(entity, user.getUserName());
			} catch (Exception e) {	System.out.println("!!!没有自动插入的字段");}
			getSqlSession().insert(entity.getClass().getSimpleName() + "_insert", entity);
			logger.debug("save:"+entity);
			return entity;
		} catch (RuntimeException re) {
			logger.error("insert " + entity.getClass().getName() + " failed :{}",re);
			throw re;
		}
	}

	public void saveList(List<T> entitys){
		for (T t : entitys) {
			save(t);
		}
	}
	
	/**
	 * update(更新)
	 * @param entity
	 * @throws RuntimeException
	 */
	@SuppressWarnings("rawtypes")
	public void update(T entity) {
		Class modelClass=entity.getClass();

		try {
			//设置更新时间
//			TimeUtil.setTime(entity,"updateTime");
			
			try {
				Method setUpdateTime= modelClass.getDeclaredMethod("setLastUpdateDatetime",Date.class);
				setUpdateTime.invoke(entity, new Date());
			} catch (Exception e) {
				// TODO 
				System.out.println("!!!没有自动插入的字段");
			}
			getSqlSession().update(entity.getClass().getSimpleName() + "_update", entity);
			logger.debug("edit:"+entity);
		} catch (RuntimeException re) {
			logger.error("update " + entity.getClass().getName()+ " failed :{}", re);
			throw re;
		} 
	}
	/**
	 * 批量更新
	 * @param entitys 类
	 * @return T 
	 * @throws RuntimeException
	 */
	public void updateList(List<T> entitys){
		for (T t : entitys) {
			update(t);
		}
	}
	/**
	 * delete(通过id删除）
	 * @param id
	 * @return T 
	 * @throws RuntimeException
	 */
	public T removeById(PK id) {
		T t=(T) findById(id);
		try {
			getSqlSession().delete(getClassName() + "_deleteById", id);
			logger.debug("remove:"+id);
			return t;
		} catch (RuntimeException re) {
			logger.error("delete " + getClassName() + "failed :{}", re);
			throw re;
		}
		
	}

	/**
	 * findById(通过id查询）
	 * @param id
	 * @return T 
	 * @throws RuntimeException
	 */
	@Override
	public T findById(PK id) {
		try {
			return (T) getSqlSession().selectOne(getClassName() + "_getObjectById", id);
		} catch (RuntimeException re) {
			logger.error("findById " + getClassName() + "failed :{}",re);
			throw re;
		}
	}
	

	/**
	 * findAll(查询所有）
	 * @return T
	 * @throws RuntimeException
	 */
	public List<T> findAll() {
		try {
			return getSqlSession().selectList(getClassName() + "_getList");
		} catch (RuntimeException re) {
			logger.error("findAll " + getClassName() + "failed :{}",re);
			throw re;
		}
	}

	@Override
	public PageList findPage(T entity, int currentPage, int pageSize) {
		int totalCount=findCount(entity);
		PageList pageList=new PageList(totalCount,pageSize,currentPage);
		pageList.updateIndex();
		if(entity!=null){
			((BaseModel)entity).setStartRow((pageList.getCurrentPage()-1) * pageList.getPageSize());
			((BaseModel)entity).setEndRow(pageSize);
		}
		if(totalCount>0){
			List<T> entityList=getSqlSession().selectList(getClassName() + "_getListForPage",entity);
			pageList.setDataList(entityList);
		}
		return pageList;
	}
	
	public Integer findCount(T entity){
		return Integer.valueOf(getSqlSession().selectOne(getClassName() + "_getCount", entity).toString());
	}
	
	public List<T> findList(T entity){
		try {
			return getSqlSession().selectList(getClassName() + "_getList",entity);
		} catch (RuntimeException re) {
			logger.error("findAll " + getClassName() + "failed :{}",re);
			throw re;
		}
	}

	@Override
	public void removeByObject(T entity) {
		try {
			getSqlSession().delete(getClassName() + "_deleteByObject", entity);
		} catch (RuntimeException re) {
			logger.error("delete " + getClassName() + "failed :{}", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListBySql(String myBatisSqlId, Object conditions){
		return getSqlSession().selectList(myBatisSqlId,conditions);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PageList findPage(String querySqlId, Object conditions, int currentPage,
                             int pageSize) {
		//生成查询数量的sqlId
	
		String sqlQueryCountId=querySqlId.concat("_count");
		int totalCount=0;
		if(getSqlSession().getConfiguration().getMappedStatement(sqlQueryCountId)!=null){
			totalCount= Integer.valueOf(getSqlSession().selectOne(sqlQueryCountId, conditions).toString());
		}
		PageList pageList=new PageList(totalCount,pageSize,currentPage);
		pageList.updateIndex();
		if(totalCount>0){
			Map map=(conditions instanceof Map)?(Map)conditions: ModelToMap.modelToMap(conditions);
			map.put("startRow", (pageList.getCurrentPage()-1)*pageList.getPageSize());
			map.put("endRow", pageList.getPageSize());
			List<T> entityList=getSqlSession().selectList(querySqlId,map);
			pageList.setDataList(entityList);
		}
		return pageList;
	}
	@Override
	/** 根据MyBatis的配置ID更新*/
	public  int updateBySql(String myBatisSqlId, Object params){
		return getSqlSession().update(myBatisSqlId, params);
	}

	@Override
	public long findTotalCount(String myBatisSqlId, Object params) {
		int totalCount=0;
		if(getSqlSession().getConfiguration().getMappedStatement(myBatisSqlId)!=null){
			totalCount= Integer.valueOf(getSqlSession().selectOne(myBatisSqlId, params).toString());
		}
		return totalCount;
	}
	

}
