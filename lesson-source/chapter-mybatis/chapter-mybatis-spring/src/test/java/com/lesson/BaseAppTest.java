package com.lesson;

import com.lesson.source.mybatis.spring.configure.CoreConfigure;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhengshijun
 * @version created on 2018/10/10.
 */
@ContextConfiguration(classes = CoreConfigure.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseAppTest {
}
