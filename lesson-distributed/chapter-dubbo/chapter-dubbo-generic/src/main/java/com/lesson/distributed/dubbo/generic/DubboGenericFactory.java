package com.lesson.distributed.dubbo.generic;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhengshijun
 * @version created on 2018/12/5.
 */
public final class DubboGenericFactory {


    private static String invokerUrl = "dubbo://192.168.112.1:20880";


    private static ApplicationConfig applicationConfig = new ApplicationConfig();
    static {
        applicationConfig.setName("genericTest");

        applicationConfig.setOwner("zhengshijun");

        applicationConfig.setOrganization("test.io");



    }


    public static GenericService get(String interfaceName) {




        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();

        reference.setApplication(applicationConfig);

        reference.setInterface(interfaceName);

        reference.setGeneric(true);
        reference.setProtocol("dubbo");
        reference.setId(interfaceName+"#id");
        reference.setRetries(0);

        if (StringUtils.isNotBlank(invokerUrl)) {
            reference.setUrl(invokerUrl);
        } else {
            RegistryConfig registry = new RegistryConfig();
            registry.setCheck(false);
            registry.setAddress("121.196.232.248:2181");
            registry.setProtocol("zookeeper");

            reference.setRegistry(registry);
        }




        ReferenceConfigCache referenceConfigCache = ReferenceConfigCache.getCache();
        return referenceConfigCache.get(reference);
    }



    public static void main(String[] args){

        GenericService genericService = get("com.chapter.distributed.dubbo.service.IUserInfoService");




        Object result = genericService.$invoke("getName",new String[]{String.class.getName()},new Object[]{"zsj"});


        System.out.println(result);
    }










}
