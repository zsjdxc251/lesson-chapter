package com.lesson.source.mybatis.spring.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
* @author Mybatis Generator
* @version created on 2019/01/23.
*/
@Table(name = "t_user_bank_info")
@Alias("cCCCCCCCCUserBankInfo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBankInfo extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "bank_first_name")
    private String bankFirstName;

    @Column(name = "bank_middle_name")
    private String bankMiddleName;

    @Column(name = "bank_last_name")
    private String bankLastName;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "status")
    private Byte status;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "json_bank_images")
    private String jsonBankImages;

    /**
     * 支行信息
     */
    @Column(name = "sub_branch")
    private String subBranch;
}