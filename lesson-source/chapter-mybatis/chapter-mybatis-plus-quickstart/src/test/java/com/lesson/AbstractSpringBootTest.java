package com.lesson;

import com.lesson.source.mybatis.plus.quickstart.MybatisPlusQuickstartBootstrap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhengshijun
 * @version created on 2019/6/13.
 */

@SpringBootTest(classes = MybatisPlusQuickstartBootstrap .class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AbstractSpringBootTest {
}
