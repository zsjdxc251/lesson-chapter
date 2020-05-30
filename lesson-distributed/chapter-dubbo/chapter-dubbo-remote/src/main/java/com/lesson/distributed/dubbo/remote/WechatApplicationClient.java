package com.lesson.distributed.dubbo.remote;

import com.yh.dubbo.wechat.IWechatService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2020/4/29.
 */
@EnableDubbo
@DubboComponentScan
@SpringBootApplication
public class WechatApplicationClient {

	public static void main(String[] args) {

		SpringApplication.run(WechatApplicationClient.class,args);




	}

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("estate");
		applicationConfig.setQosEnable(Boolean.FALSE);
		return applicationConfig;
	}


	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
		registryConfig.setCheck(Boolean.FALSE);
		return registryConfig;
	}

	@Bean
	public ReferenceConfig<IWechatService> dubboWechatService(){
		ReferenceConfig<IWechatService> service =  new ReferenceConfig<>();
		service.setCheck(Boolean.FALSE);
		service.setInterface(IWechatService.class.getName());
		service.setTimeout(6000);
		service.setProtocol("dubbo");
		return service;
	}

	@Bean
	public IWechatService wechatService(ReferenceConfig<IWechatService> referenceConfig){

		return referenceConfig.get();
	}

	@Bean
	public ApplicationRunner applicationRunner(@Autowired IWechatService wechatService){
		return arguments->{


			long start = System.currentTimeMillis();
			Map<String, Object> map = wechatService.getJsApi("http://baidu.com");
			System.out.println(System.currentTimeMillis() - start);

			System.out.println(map);
		};
	}

}
