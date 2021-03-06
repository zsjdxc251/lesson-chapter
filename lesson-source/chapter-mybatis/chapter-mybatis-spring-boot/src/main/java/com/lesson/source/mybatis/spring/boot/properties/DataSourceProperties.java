package com.lesson.source.mybatis.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 *
 *  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
 * 		<!-- 基本属性 url、user、password -->
 * 		<property name="url" value="${jdbc.url}"/>
 * 		<property name="username" value="${jdbc.username}"/>
 * 		<property name="password" value="${jdbc.password}"/>
 * 		<!-- 配置初始化大小、最小、最大 -->
 * 		<property name="initialSize" value="${jdbc.initialSize}"/>
 * 		<property name="minIdle" value="${jdbc.minIdle}"/>
 * 		<property name="maxActive" value="${jdbc.maxActive}"/>
 *
 * 		<!-- 配置获取连接等待超时的时间，单位是毫秒 -->
 * 		<property name="maxWait" value="60000"/>
 *
 * 		<!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
 * 		<property name="timeBetweenEvictionRunsMillis" value="60000"/>
 *
 * 		<!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
 * 		<property name="minEvictableIdleTimeMillis" value="300000"/>
 *
 * 		<property name="validationQuery" value="SELECT 'x'"/>
 * 		<property name="testWhileIdle" value="true"/>
 * 		<property name="testOnBorrow" value="false"/>
 * 		<property name="testOnReturn" value="false"/>
 *
 * 		<!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
 * 		<!-- 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false -->
 * 		<property name="poolPreparedStatements" value="false"/>
 * 		<property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>
 *
 * 		<!-- 配置监控统计拦截的filters -->
 * 		<property name="filters" value="config,stat,wall"/>
 * 		<property name="connectionProperties" value="config.decrypt=true;config.decrypt.key=${jdbc.config.decrypt.key}" />
 * 	</bean>
 * @author zhengshijun
 * @version created on 2018/12/21.
 */
@ConfigurationProperties(prefix = "jdbc.data-source")
public class DataSourceProperties extends Properties {
}
