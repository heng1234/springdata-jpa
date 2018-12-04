package com.hvly.springjp_1.com.hlvy.controller;

import com.hvly.springjp_1.com.hlvy.entity.Customer;
import com.hvly.springjp_1.com.hlvy.repository.CustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomerController
 *
 * @author heng
 **/
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    /**
     * @NamedQuery查询
     * @param firstName
     * @return
     */
    @RequestMapping("findByFirstName")
    Customer findByFirstName(String firstName){
        return customerJpaRepository.findByFirstName(firstName);
    }
    /**
     * @NamedQuery查询
     * @param id
     * @return
     */
    @RequestMapping("findByCusId")
    Customer findByCusId(Long id){
        return customerJpaRepository.findByCusId(id);
    }
}
