package com.lesson.source.mybatis.plus.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhengshijun
 * @version created on 2019/2/28.
 */
@Data
@Accessors(chain = true)
public abstract class BaseEntity {

	private Long id;
}
