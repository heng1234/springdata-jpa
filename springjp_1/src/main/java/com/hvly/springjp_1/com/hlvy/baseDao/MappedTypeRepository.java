package com.hvly.springjp_1.com.hlvy.baseDao;

import com.hvly.springjp_1.com.hlvy.baseDao.AbstractMappedType;
import com.hvly.springjp_1.com.hlvy.entity.SeLPUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * MappedTypeRepository
 * SqEL
 * @author heng
 **/
@NoRepositoryBean
public interface MappedTypeRepository<T extends AbstractMappedType> extends Repository<T,Long> {


}
