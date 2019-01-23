package com.lesson.source.mybatis.spring.mapper;

import com.lesson.source.mybatis.spring.model.UserBankInfo;
import com.lesson.source.mybatis.spring.model.UserBankInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @author Mybatis Generator
* @version created on 2019/01/23.
*/
public interface UserBankInfoMapper {
    long countByExample(UserBankInfoExample example);

    int deleteByExample(UserBankInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBankInfo record);

    int insertSelective(UserBankInfo record);

    List<UserBankInfo> selectByExample(UserBankInfoExample example);

    UserBankInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBankInfo record, @Param("example") UserBankInfoExample example);

    int updateByExample(@Param("record") UserBankInfo record, @Param("example") UserBankInfoExample example);

    int updateByPrimaryKeySelective(UserBankInfo record);

    int updateByPrimaryKey(UserBankInfo record);
}