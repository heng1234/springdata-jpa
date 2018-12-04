package com.hvly.springjp_1.com.hlvy.repository;

import com.hvly.springjp_1.com.hlvy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Customer
 *
 * @author heng
 **/
public interface CustomerJpaRepository extends JpaRepository<Customer,Long>{

    /**
     * @NamedQuery查询
     * @param firstName
     * @return
     */
    Customer findByFirstName(String firstName);
    /**
     * @NamedQuery查询
     * @param id
     * @return
     */
    Customer findByCusId(Long id);
}
