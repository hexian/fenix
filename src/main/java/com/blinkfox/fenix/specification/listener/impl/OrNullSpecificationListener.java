package com.blinkfox.fenix.specification.listener.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Component;

import com.blinkfox.fenix.specification.annotation.OrNull;
import com.blinkfox.fenix.specification.listener.AbstractListener;

/**
 * OrNullSpecificationListener
 * @description Or xxx is null
 * @author YangWenpeng
 * @date 2019年5月5日 下午4:33:32
 * @version v1.0.0
 */
@Component
public class OrNullSpecificationListener extends AbstractListener {

    /**
     * @see com.thunisoft.framework.jpaplus.specification.listener.AbstractListener#getAnnotation() 
     */
    @SuppressWarnings("unchecked")
    @Override
    public  Class<OrNull> getAnnotation() {
        return OrNull.class;
    }

    /**
     * @see com.thunisoft.framework.jpaplus.specification.listener.AbstractListener#buildPredicate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root, java.lang.String, java.lang.Object) 
     */
    @Override
    protected <Z,X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z,X> from, String name, Object value,
        Object annotation) {
        return criteriaBuilder.or(criteriaBuilder.isNull(from.get((String)value)));
    }

}
