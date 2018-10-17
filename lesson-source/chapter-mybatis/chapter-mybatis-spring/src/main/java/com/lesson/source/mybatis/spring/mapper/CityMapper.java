package com.lesson.source.mybatis.spring.mapper;

import com.lesson.source.mybatis.spring.model.City;
import com.lesson.source.mybatis.spring.model.CityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    long countByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int deleteByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int insert(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int insertSelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    List<City> selectByExample(CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    @Select("select * from city  where id=#{id}")
    City selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(City record);

    @Select("select * from city")
    List<City> selectAll();
}