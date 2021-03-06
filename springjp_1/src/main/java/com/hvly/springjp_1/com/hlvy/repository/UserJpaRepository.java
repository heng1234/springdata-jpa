package com.hvly.springjp_1.com.hlvy.repository;

import com.hvly.springjp_1.com.hlvy.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * UserRepository
 *
 * @author heng
 **/
public interface UserJpaRepository extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {
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

    /**
     * 使用Sort进行排序
     * @param name
     * @param sort
     * @return
     */
    @Query("select u from User u where name like %?1%")
    List<User> findByAndSort(String name, Sort sort);

    /**
     * 使用@Query分页
     * @param pageable
     * @return
     */
    @Query("select u from User u")
    Page<User> findByAllPage(Pageable pageable);

    /**
     * 使用原生sql进行分页
     * 1.59版本需要这样写    select * from User /* #pageable# */
    /**
     * @param pageable
     * @return
     */
    @Query(value = "select * from User  ", countQuery = "select count(*) from User",nativeQuery = true)
    Page<User> findBySqlAllPage(Pageable pageable);

    /**
     * 使用@Param查询
     * @param id
     * @param name
     * @return
     */
    @Query("select u from User u where u.id = :id or u.name = :name")
    List<User> findLastNameOrFirstName(@Param("id") Long id, @Param("name") String name);

    /**
     * 使用Spel表达式查询  #{#entityName} 1、如果定义了@Entity注解直接使用属性名 2、如果没有定义直接使用实体类名
     * @return
     */
    @Query("select u  from  #{#entityName} u")
    List<User> findBySpelAll();

    /**
     * @Modifying修改查询 根据id修改name
     * @param id
     * @param name
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)//clearAutomatically = true刷新hibernate一级缓存
    @Query("update User u set u.name = ?2 where u.id = ?1")
    int updateUser(Long id,String name);
    /**
     * @Modifying删除查询
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)//clearAutomatically = true刷新hibernate一级缓存
    @Query("delete from User u  where u.id = :id")
    int deleteUser(@Param("id") Long id);


    /**
     * @QueryHints查询    ps: QueryHint仅仅了解下即可 一般业务场景基本不用
     * @param name
     * @param page
     * @return
     */
    @QueryHints(value = {@QueryHint(name="name",value="value")},forCounting = false)
    Page<User> findByNameOrId(String name,Long id,Pageable page);


    /**
     * @Procedure 的 procedureName 参数必须匹配 @NamedStoredProcedureQuery 的procedureName
     * @Procedure 的 name参书必须匹配@NamedStoredProcedureQuery 的 name
     * @Param必须匹配@StoredProcedureParameter的name参数
     * 返回类型必须匹配: in_only_test 存储过程返回是void,in_and_out_test存储过程必须String
     * */

    /**
     * 调用存储过程
     * pluslinout 存储过程名字
     * @param arg
     * @return
     */
    @Procedure("pluslinout")
    Integer explicitlyNamedPluslinout(Integer arg);
    /**
     * 调用存储过程
     * pluslinout 存储过程名字
     * @param arg
     * @return
     */
    @Procedure(procedureName = "pluslinout")
    Integer pluslinout(Integer arg);

    /**
     *  User.pluslIO自定义存储过程的名字
     * @param arg
     * @return
     */
    @Procedure(name = "User.plusl")
    Integer entityAnnotatedCustomNamedProcedurePluslIO(@Param("arg") Integer arg);


}
