package com.hvly.springjp_1.com.hlvy.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * User
 *
 * @author heng
 **/
@Data//lombok里的
@Entity(name = "User")

/**
 *  @Procedure存储过程查询方法
 *  调用数据库存储过程需要在实体类定义定义
 * name: 在EntityManager中的名字 NamedStoredProcedureQuery使用
 * procedureName: 数据库里存储过程的名字
 * parameters: 使用IN/OUT参数
 * 
 * 存储过程使用了注解@NamedStoredProcedureQuery 并绑定到一个JPA表。
 * procedureName是存储过程的名字
 * name是JPA中存储过程的名字
 * 使用注解@StoredProcedureParameter来定义存储过程使用的IN/OU参书
 */

@NamedStoredProcedureQuery(name = "User.plusl",procedureName = "pluslinout",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,name = "arg",type=Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT,name="res",type = Integer.class)
})
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动增递
    private Long id;

    private String name;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
