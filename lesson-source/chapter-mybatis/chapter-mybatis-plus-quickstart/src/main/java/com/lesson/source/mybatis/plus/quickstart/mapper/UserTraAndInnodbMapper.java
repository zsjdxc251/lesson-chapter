package com.lesson.source.mybatis.plus.quickstart.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodb;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/7/13.
 */
public interface UserTraAndInnodbMapper extends BaseMapper<UserTraAndInnodb> {

    @Select("select ${ew.sqlSelect} from t_user_tra tut left join t_user_innodb tui ON tut.id = tui.id ${ew.customSqlSegment} ")
    List<UserTraAndInnodb> selectTraAndInnAll(@Param("ew") Wrapper<UserTraAndInnodb> wrapper);
}
