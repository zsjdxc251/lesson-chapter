package com.lesson.source.mybatis.plus.quickstart.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhengshijun
 * @version created on 2019/7/12.
 */
@Data

public class UserTraAndInnodb {


    @TableField("tut.username")
    private String username;

    @TableField("tut.password")
    private String password;


    @TableField("tui.phone_number")
    private Long phoneNumber;

}
