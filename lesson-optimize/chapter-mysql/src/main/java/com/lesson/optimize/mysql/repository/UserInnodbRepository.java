package com.lesson.optimize.mysql.repository;

import com.lesson.optimize.mysql.entity.Store;
import com.lesson.optimize.mysql.entity.UserInnodb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author zhengshijun
 * @version created on 2019/6/14.
 */
public interface UserInnodbRepository extends CrudRepository<UserInnodb, Long> {
}
