package com.lesson.optimize.mysql.repository;

import com.lesson.optimize.mysql.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author zhengshijun
 * @version created on 2019/6/14.
 */
public interface StoreRepository extends JpaSpecificationExecutor<Store> {
}
