package com.lesson.source.mybatis.custom.mapper;

import com.lesson.source.mybatis.custom.entity.FlowRecord;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 流水记录 Mapper 接口
 * </p>
 *
 * @author zhengshijun
 * @since 2019-02-28
 */
public interface FlowRecordMapper {

	FlowRecord selectOne();

	@Select("select * from flow_record limit 1")
	FlowRecord selectOne1();


	/**
	 *
	 * @param name
	 * @return
	 */
	@Select("select * from flow_record limit 1")
	FlowRecord selectOne12(String name);

}
