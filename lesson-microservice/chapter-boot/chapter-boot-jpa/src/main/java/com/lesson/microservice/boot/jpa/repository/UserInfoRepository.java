package com.lesson.microservice.boot.jpa.repository;

import com.lesson.microservice.boot.jpa.entity.UserInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo,Long> {
}
