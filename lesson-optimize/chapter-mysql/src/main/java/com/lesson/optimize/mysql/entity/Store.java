package com.lesson.optimize.mysql.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhengshijun
 * @version created on 2019/6/14.
 */
@Data
@Table(name = "store")
@Entity
public class Store {

    @Id
    private String id;

    /**
     *  全部写成小写
     */
    @Column(name = "memberid")
    private String memberId;

    private String areacode;

    private String address;

    private String mobile;


}
