package com.hvly.springjp_1.com.hlvy.controller;

import com.hvly.springjp_1.com.hlvy.entity.SeLPUser;
import com.hvly.springjp_1.com.hlvy.entity.User;
import com.hvly.springjp_1.com.hlvy.repository.SpELUserRepository;
import com.hvly.springjp_1.com.hlvy.repository.UserAtRepository;
import com.hvly.springjp_1.com.hlvy.repository.UserJpaRepository;
import com.hvly.springjp_1.com.hlvy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
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

    @Autowired
    private SpELUserRepository spELUserRepository;


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

    /**
     * 使用Sort排序
     * @param name
     * @return
     */
    @RequestMapping("findByAndSort")
    public List<User> findByAndSort(String name){
        return (List<User>) userJpaRepository.findByAndSort(name, new Sort(new Sort.Order( Sort.Direction.DESC,"id")));
    }

    /**
     * 使用jpaSort排序
     * @param name
     * @return
     */
    @RequestMapping("findByAndJpaSort")
    public List<User> findByAndJpaSort(String name){
        return (List<User>) userJpaRepository.findByAndSort(name, JpaSort.unsafe(JpaSort.Direction.DESC,"id"));
    }

    /**
     * 使用@Query进行分页
     * @return
     */
    @RequestMapping("findByAllPage")
    public Page<User> findByAllPage(int page){
        if(page<0){
            page=0;
        }
        return  userJpaRepository.findByAllPage(new PageRequest(page,3));
    }

    /**
     * 使用原生sql进行分页
     * @param page
     * @return
     */
    @RequestMapping("findBySqlAllPage")
    public Page<User> findBySqlAllPage(int page){
        if(page<0){
            page=0  ;
        }
        return  userJpaRepository.findBySqlAllPage(new PageRequest(page,3, Sort.Direction.ASC,"id"));
    }

    /**
     * 使用@Param进行查询
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("findLastNameOrFirstName")
    public List<User> findLastNameOrFirstName(Long id,String name){

        return  userJpaRepository.findLastNameOrFirstName(id,name);
    }

    /**
     * 使用Spel表达式查询
     * @return
     */
    @RequestMapping("findBySpelAll")
    public List<User> findBySpelAll(){

        return  userJpaRepository.findBySpelAll();
    }

    /**
     * 使用Spel表达式公用类查询
     * @return
     */
    @RequestMapping("spelfindBySpelAll")
    public List<SeLPUser> spelfindBySpelAll(){

        return  spELUserRepository.findBySpelAll();
    }

    /**
     * @Modifying修改查询 根据id修改name
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("updateUer")
    public String updateUer(Long id,String name){
        int res = userJpaRepository.updateUser(id,name);
        return res>0?"update success":"update error";
    }

    /**
     * @Modifying删除查询 根据id删除User
     * @param id
     * @return
     */
    @RequestMapping("deleteUser")
    public String saveUser(Long id){
        int res = userJpaRepository.deleteUser(id);
        return res>0?"delete success":" ， error";
    }



    /**
     * @QueryHints查询    ps: QueryHint仅仅了解下即可 一般业务场景基本不用
     * @param name
     * @return
     */
    @RequestMapping("findByNameueryHints")
    public Page<User> findByNameueryHints(String name,Long id){
        return userJpaRepository.findByNameOrId(name,id,new PageRequest(0,3));
    }

    /**
     * @Procedure
     * 调用存储过程
     */
    @RequestMapping("explicitlyNamedPluslinout")
    public Integer explicitlyNamedPluslinout(int arg){
       // return userJpaRepository.explicitlyNamedPluslinout(arg);//调用成功
       // return userJpaRepository.pluslinout(arg);//调用成功
        return userJpaRepository.entityAnnotatedCustomNamedProcedurePluslIO(arg);//调用成功
    }



}
