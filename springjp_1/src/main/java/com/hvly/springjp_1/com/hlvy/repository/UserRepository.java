package com.hvly.springjp_1.com.hlvy.repository;

import com.hvly.springjp_1.com.hlvy.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UserRepository
 *
 * @author heng
 **/
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

}
