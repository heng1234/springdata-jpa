package com.hvly.springjp_1.com.hlvy.repository;

import com.hvly.springjp_1.com.hlvy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * UserRepository
 *
 * @author heng
 **/
public interface UserJpaRepository extends JpaRepository<User,Long> {
    /**
     * 使用jpql查询
     * nativeQuery默认false 注释:是否启用原生sql
     * @param name
     * @return
     */
    @Query(value = "select u.* from user u where u.name = ?1",nativeQuery=true)
    List<User> findByName(String name);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);


    /**
     * 根据name前缀模糊查询
     * @param name
     * @return
     */
    @Query("select u from User u where u.name like %?1")
    List<User> findByNameEndsWith(String name);

    /**
     * nativeQuery正确排序方式
     * 原生sql 根据id排序
     * @param idName 这个是数据库字段名
     * @return
     */
    @Query(value = "select * from User order by ?1 desc ",nativeQuery = true)
    List<User> findByFirstName(String idName);
}
