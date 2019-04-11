package com.lesson.source.mybatis.custom.entity;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 流水记录
 * </p>
 *
 * @author zhengshijun
 * @since 2019-02-28
 */

public class FlowRecord {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */

	private Long userId;

	/**
	 * 订单id
	 */

	private Long orderId;

	/**
	 * 流水名称
	 */

	private String name;

	/**
	 * 总价
	 */

	private BigDecimal sum;

	/**
	 * 创建时间
	 */

	private LocalDateTime createTime;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
