package com.lesson.source.mybatis.plus.ant.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesson.source.mybatis.plus.AbstractSpringBootTest;
import com.lesson.source.mybatis.plus.ant.entity.FlowRecord;
import com.lesson.source.mybatis.plus.ant.service.IFlowRecordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlowRecordServiceImplTest extends AbstractSpringBootTest {

	@Autowired
	private IFlowRecordService flowRecordService;

	@Test
	public void test1(){


		FlowRecord flowRecord = new FlowRecord();
		flowRecord.setName("1");
		flowRecord.setOrderId(1L);
		flowRecord.setId(22L);
		flowRecord.setUserId(45L);
		flowRecord.setCreateTime(LocalDateTime.now());
		flowRecord.setSum(new BigDecimal(22L));

		flowRecord.setId(2L);
		flowRecordService.save(flowRecord);

		QueryWrapper<FlowRecord> wrapper = new QueryWrapper<>();

		wrapper.eq("id",22);
		flowRecordService.getOne(wrapper);


	}

}