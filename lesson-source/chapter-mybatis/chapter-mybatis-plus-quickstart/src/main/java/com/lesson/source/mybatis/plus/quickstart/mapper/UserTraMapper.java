package com.lesson.source.mybatis.plus.quickstart.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTra;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodb;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodbTwo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/7/5.
 */
public interface UserTraMapper extends BaseMapper<UserTra> {


    @Select("select ${ew.sqlSelect} from t_user_tra tut left join t_user_innodb tui ON tut.id = tui.id ${ew.customSqlSegment} ")
    List<UserTraAndInnodbTwo> selectTraAndInnAll(@Param("ew") Wrapper<UserTraAndInnodbTwo> wrapper);
}
