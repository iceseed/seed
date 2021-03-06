package com.ice.seed.common.persistence.criteria;

import lombok.Data;
import tk.mybatis.mapper.entity.Example;

/**
 * 通用查询条件
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/10/24
 */
@Data
public class QueryCriteria extends Example {

    /*是否只查一条*/
    private boolean selectFirst = false;
    /*当前页索引*/
    private int pageIndex = 1;
    /*页面大小*/
    private int pageSize = 20;
    /*指定第一个返回记录行的偏移量*/
    private Integer offset = 0;
    /*返回记录行的最大数目*/
    private Integer limit;
    /*版本号，用于乐观锁控制*/
    private Object version;

    public QueryCriteria(Class<?> entityClass) {
        super(entityClass);
    }

    public QueryCriteria(Class<?> entityClass, boolean exists) {
        super(entityClass, exists);
    }

    public QueryCriteria(Class<?> entityClass, boolean exists, boolean notNull) {
        super(entityClass, exists, notNull);
    }

    public boolean isSelectFirst() {
        return selectFirst;
    }

}
