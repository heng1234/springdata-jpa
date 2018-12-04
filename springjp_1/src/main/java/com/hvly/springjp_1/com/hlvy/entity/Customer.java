package com.hvly.springjp_1.com.hlvy.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Customer
 *
 * @author heng
 **/

/**
 * 1、在@Entity下增加@NamedQuery定义 需要注意 query里面的值也是JPQL 查询参数也要和实体类对应起来
 * 因为实际场景中这种破坏ENtity的侵入式很不美 也不方便 所以这种方式容易一万 工作中也很少推荐
 * 2、与之相对应的还有@NamedNativeQuery 用法一样 唯一不同的是 query里面放置的是原生SQL语句 而非实体类的字段名字
 * name query的名称 规则：实体类.方法名`
 */

/*@NamedQuery(name = "Customer.findByFirstName",
        query = "select c from Customer c where c.firstname = ?1")*/

/**
 * @NamedQuery 集合查询
 */
@NamedQueries({@NamedQuery(name = "Customer.findByFirstName",
        query = "select c from Customer c where c.firstname = ?1"),
        @NamedQuery(name = "Customer.findByCusId",
                query = "select c from Customer c where c.id = ?1")})
//@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动增递
    private Long id;

    private String firstname;

    private String lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}