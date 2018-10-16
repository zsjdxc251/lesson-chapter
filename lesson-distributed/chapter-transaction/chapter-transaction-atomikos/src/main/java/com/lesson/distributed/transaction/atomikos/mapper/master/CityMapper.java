package com.lesson.distributed.transaction.atomikos.mapper.master;

import com.lesson.distributed.transaction.atomikos.entity.City;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
public interface CityMapper {

    City selectOne();

    int insert(City city);
}
