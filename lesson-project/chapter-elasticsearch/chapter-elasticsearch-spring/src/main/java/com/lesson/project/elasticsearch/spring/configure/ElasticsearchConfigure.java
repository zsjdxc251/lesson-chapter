package com.lesson.project.elasticsearch.spring.configure;

import org.elasticsearch.client.transport.TransportClient;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhengshijun
 * @version created on 2018/12/4.
 */
@PropertySource("classpath:application.properties")
@ComponentScan("com.lesson.project.elasticsearch.spring")
public class ElasticsearchConfigure {



    @Autowired
    private ElasticsearchProperties elasticsearchProperties;


    @Bean
    private TransportClient elasticsearchClient(){

        TransportClient transportClient = null;
        try {
            Settings setting = Settings.builder()
                    .put("cluster.name",elasticsearchProperties.getClusterName())
                    .put("client.transport.sniff",true)
                    .build();

            transportClient =
                    new PreBuiltTransportClient(setting);

            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(elasticsearchProperties.getHost()),elasticsearchProperties.getPort());
            transportClient.addTransportAddress(transportAddress);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException();
        }
        return transportClient;

    }


}
