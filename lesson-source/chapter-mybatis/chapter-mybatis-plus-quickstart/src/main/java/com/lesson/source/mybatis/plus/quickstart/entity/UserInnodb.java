package com.lesson.source.mybatis.plus.quickstart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhengshijun
 * @version created on 2019/6/16.
 */
@Data

@TableName(value = "t_user_innodb")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInnodb {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private Long phoneNumber;

    private String password;

    private Integer age;

    private String email;

    private String address;

    private Byte gender;
}
