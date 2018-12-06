package com.lesson.project.elasticsearch.quickstart;

import com.lesson.project.elasticsearch.quickstart.sample.SampleSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhengshijun
 * @version created on 2018/11/27.
 */
@Slf4j
public class TransportClientSupport {



    public static void start(SampleSupport sampleSupport){
        TransportClient client = null;

        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "first_cluster").put("client.transport.sniff", true).build();  //设置ES实例的名称
            client= new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("121.196.232.248"), 9300));
        } catch (UnknownHostException e) {
            log.error(StringUtils.EMPTY,e);
        }


        if (client != null){

            sampleSupport.request(client);

            client.close();
        }
    }
}
