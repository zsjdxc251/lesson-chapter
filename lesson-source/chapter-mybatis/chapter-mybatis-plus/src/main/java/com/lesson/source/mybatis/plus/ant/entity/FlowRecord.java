package com.lesson.source.mybatis.plus.ant.entity;

import java.math.BigDecimal;
import com.lesson.source.mybatis.plus.common.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 * <p>
 * 流水记录
 * </p>
 *
 * @author zhengshijun
 * @since 2019-02-28
 */

@Table(name = "flow_record")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FlowRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 流水名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 总价
     */
    @Column(name = "sum")
    private BigDecimal sum;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;



}
