package com.ice.seed.common.service;

import java.util.List;

import com.ice.seed.common.persistence.Query;
import com.ice.seed.common.persistence.pager.PageList;

/**
 * 业务接口基类
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/10/24
 */
public interface IBaseService<T> {

	/**
	 * 根据主键获得一个领域对象
	 * @param id 对象id Long类型
	 * @return 返回一个领域对象
	 */
    T get(Long id);
    
    /**
     * 根据主键对象获取一个领域对象
     * @param object 主键对象
     * @return
     * @author : IceSeed	
     * @since : 2017年7月21日
     */
    T getPrimaryKeyObj(Object object);

    /**
	 * 根据查询对象获得一个领域对象
	 * @param query 查询对象
	 * @return 返回一个领域对象
	 */
	T getOne(Query query);

	/**
	 * 根据查询对象获得匹配第一个领域对象
	 * @param query 查询对象
	 * @return 返回一个领域对象
	 */
	T getFirst(Query query);

    /**
     * 根据查询对象获得领域对象列表
     * @param query 查询对象
     * @return 返回领域对象列表
     */
    List<T> getList(Query query);

	/**
	 * 根据查询对象获得领域对象列表
	 * @param query 查询对象
	 * @return 返回领域对象列表
	 */
	List<T> getListNoDesc(Query query);

    /**
     * 根据查询对象获得领域对象分页列表
     * @param query 查询对象
     * @return 返回领域对象分页列表
     */
    PageList<T> getPageList(Query query);

	/**
	 * 根据查询对象获得领域对象分页列表
	 * @param query 查询对象
	 * @return 返回领域对象分页列表
	 */
	PageList<T> getPageListNoDesc(Query query);

	/**
	 * 根据查询对象获得指定行范围的领域对象列表
	 * @param query
	 * @return
	 */
	List<T> getSliceList(Query query);

	/**
	 * 根据查询对象获得指定行范围的领域对象列表
	 * @param query
	 * @return
	 */
	List<T> getSliceListNoDesc(Query query);

    /**
     * 通过查询对象获得领域对象统计数
     * @param query 查询对象
     * @return 返回统计数
     */
	int count(Query query);

    /**
     * 创建一个领域对象
     * @param domain 领域对象
     * @return 返回一个领域对象`
     */
    T create(T domain);

	/**
	 * 根据id删除
	 * @param id 对象id
	 */
	void delete(Long id);
	
	/**
	 * 根据id删除
	 * @param object 主键对象
	 */
	void deletePrimaryKeyObj(Object object);

	/**
	 * 根据主键更新领域对象
	 * 更新机制：根据主键更新属性不为null的值
	 * @param domain 领域对象
	 */
	void update(T domain);
	
	/**
	 * 根据主键更新领域对象
	 * 更新机制：根据主键更新实体全部字段，null值会被更新
	 * @param domain 领域对象
	 */
	void updateAll(T domain);
	
}
