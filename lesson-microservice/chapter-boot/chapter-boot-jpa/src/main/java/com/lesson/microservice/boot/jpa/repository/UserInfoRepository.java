package com.lesson.microservice.boot.jpa.repository;

import com.lesson.microservice.boot.jpa.entity.UserInfo;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
//@NoRepositoryBean
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo,Long> {
}
