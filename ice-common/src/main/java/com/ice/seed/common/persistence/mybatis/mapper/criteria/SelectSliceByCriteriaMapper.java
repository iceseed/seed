package com.ice.seed.common.persistence.mybatis.mapper.criteria;

import com.ice.seed.common.persistence.mybatis.provider.CriteriaProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/10/24
 */
public interface SelectSliceByCriteriaMapper<T>  {
    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    @SelectProvider(type = CriteriaProvider.class, method = "dynamicSQL")
    List<T> selectSliceByCriteria(Object condition);

}
