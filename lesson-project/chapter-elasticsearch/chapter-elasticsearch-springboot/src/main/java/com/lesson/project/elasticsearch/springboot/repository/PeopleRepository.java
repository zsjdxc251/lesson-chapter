package com.lesson.project.elasticsearch.springboot.repository;

import com.lesson.project.elasticsearch.springboot.document.People;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhengshijun
 * @version created on 2018/11/27.
 */
public interface PeopleRepository extends ElasticsearchRepository<People,String> {
}
