package com.lesson.source.mybatis.plus.quickstart.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhengshijun
 * @version created on 2019/7/12.
 */
@Data
@TableName("t_user_tra tut left join t_user_innodb tui ON tut.id = tui.id")
public class UserTraAndInnodbTwo {


    @TableField("tut.username")
    private String username;

    @TableField("tut.password")
    private String password;


    @TableField("tui.phone_number")
    private Long phoneNumber;

}
