package com.hvly.springjp_1.com.hlvy.controller;

import com.hvly.springjp_1.com.hlvy.entity.User;
import com.hvly.springjp_1.com.hlvy.repository.UserAtRepository;
import com.hvly.springjp_1.com.hlvy.repository.UserJpaRepository;
import com.hvly.springjp_1.com.hlvy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * UserController
 *
 * @author heng
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAtRepository userAtRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;


    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 新增
     * @param name
     * @param email
     */
    @GetMapping("addUser")
    public void addUser(@RequestParam String name, @RequestParam String email) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
    }

    /**
     * 查询所有
     * @return
     */

    @GetMapping("findUserAll")
    public List<User> findUserAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * 验证排序和分页查询方法
     * @return
     */
    @RequestMapping("pageUser")
    public Page<User> getAllUserByPage() {
        return  userRepository.findAll(new PageRequest(1,3,new Sort(new Sort.Order(Sort.Direction.ASC,"name"))));
    }

    /**
     * 分页排序
     * @return
     */

    @RequestMapping("usersWithSort")
    public List<User> getAllUsersWithSort(){

        return (List<User>) userRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC,"name")));
    }

    /**
     * 根据id或name查询
     * @return
     */
    @RequestMapping("idorname")
    public List<User> findByIdOrName(){
       return userAtRepository.findByIdOrName(null,"恒果果");
    }

    /**
     * 使用jpql查询 原生sql
     * @return
     */
    @RequestMapping("findByName")
    public List<User> findByName(){
        String name = "hlvy";
       return userJpaRepository.findByName(name);
    }

    /**
     * 根据邮箱查询
     * @return
     */
    @RequestMapping("findByEmail")
    public User findByEmail(){
        String email = "hlvy@qq.com";
       return userJpaRepository.findByEmail(email);
    }

    /**
     * name前缀模糊查询
     * @return
     */
    @RequestMapping("findByNameEndsWith")
    public List<User> findByNameEndsWith(String name){
       return userJpaRepository.findByNameEndsWith(name);
    }

    /**
     * 根据传入的idName排序
     * @param idName
     * @return
     */
    @RequestMapping("findByIdSort")
    public List<User> findByIdSort(String idName){
        System.err.println(idName);
        String sql = "select  id,name,email from User order by "+idName + " desc";
        Query query = entityManager.createQuery(sql);
        List<User> list = query.getResultList();
        entityManager.close();
        return  list;
//       return userJpaRepository.findByFirstName(idName);
    }
}
