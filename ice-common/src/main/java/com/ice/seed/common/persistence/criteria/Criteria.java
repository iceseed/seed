package com.ice.seed.common.persistence.criteria;

import tk.mybatis.mapper.entity.Example;

/**
 * @since : 2016年11月9日
 * @author : iceSeed
 * @version : v0.0.1
 */
public  class Criteria {

    private Example.Criteria criteria;
    public Criteria(Example.Criteria innerCriteria){
        criteria = innerCriteria;
    }

    public Example.Criteria getCriteria() {
        return criteria;
    }
}
