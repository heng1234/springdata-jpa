package com.hvly.springjp_1.com.hlvy.repository;

import com.hvly.springjp_1.com.hlvy.baseDao.MappedTypeRepository;
import com.hvly.springjp_1.com.hlvy.entity.SeLPUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * SpELUserRepository
 * SpEL
 * @author heng
 **/
public interface SpELUserRepository extends MappedTypeRepository<SeLPUser> {

    /**
     * 使用Spel表达式查询  #{#entityName} 1、如果定义了@Entity注解直接使用属性名 2、如果没有定义直接使用实体类名
     * @return
     */
    @Query("select u  from  #{#entityName} u")
    List<SeLPUser> findBySpelAll();
}
