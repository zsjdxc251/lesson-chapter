package com.lesson.source.mybatis.plus.ant.entity;

import com.lesson.source.mybatis.plus.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 * <p>
 * 
 * </p>
 *
 * @author zhengshijun
 * @since 2019-02-28
 */

@Table(name = "user_info")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "age")
    private Integer age;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;


}
