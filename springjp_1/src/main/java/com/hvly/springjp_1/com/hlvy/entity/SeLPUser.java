package com.hvly.springjp_1.com.hlvy.entity;


import com.hvly.springjp_1.com.hlvy.baseDao.AbstractMappedType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User
 *
 * @author heng
 **/
@Data//lombok里的
@Entity
public class SeLPUser extends AbstractMappedType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动增递
    private Long id;

    private String name;


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

}
