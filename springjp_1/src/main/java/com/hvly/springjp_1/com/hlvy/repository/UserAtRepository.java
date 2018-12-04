package com.hvly.springjp_1.com.hlvy.repository;

import com.hvly.springjp_1.com.hlvy.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * UserAtRepository
 *
 * @author heng
 **/
public interface UserAtRepository extends Repository<User,Long> {

    /**
     * 包含查询的id或者name
     * @param id
     * @param name
     * @return
     */
     List<User> findByIdOrName(Long id,String name);


}
