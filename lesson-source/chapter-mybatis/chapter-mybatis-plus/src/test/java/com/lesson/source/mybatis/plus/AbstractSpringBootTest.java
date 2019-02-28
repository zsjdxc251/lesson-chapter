package com.lesson.source.mybatis.plus;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhengshijun
 * @version created on 2019/1/11.
 */
@SpringBootTest(classes = MybatisPlusBootstrap.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class AbstractSpringBootTest {
}
