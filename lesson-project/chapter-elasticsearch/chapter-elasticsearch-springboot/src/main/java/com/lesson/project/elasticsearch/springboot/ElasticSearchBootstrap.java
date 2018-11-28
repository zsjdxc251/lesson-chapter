package com.lesson.project.elasticsearch.springboot;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/11/27.
 */
@SpringBootApplication
public class ElasticSearchBootstrap {


    public static void main(String[] args){

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(ElasticSearchBootstrap.class,args);


        applicationContext.close();
    }

    @Primary
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client,
                                                       ElasticsearchConverter converter) {
        try {
            return new ElasticsearchTemplate(client, converter){
                @Override
                public boolean putMapping(String indexName, String type, Object mapping) {
                    Assert.notNull(indexName, "No index defined for putMapping()");
                    Assert.notNull(type, "No type defined for putMapping()");
                    PutMappingRequestBuilder requestBuilder = client.admin().indices().preparePutMapping(indexName).setType(type);
                    if (mapping instanceof String) {
                        requestBuilder.setSource(String.valueOf(mapping), XContentType.JSON);
                    } else if (mapping instanceof Map) {
                        requestBuilder.setSource((Map) mapping);
                    } else if (mapping instanceof XContentBuilder) {
                        requestBuilder.setSource((XContentBuilder) mapping);
                    }
                    return requestBuilder.execute().actionGet().isAcknowledged();
                }
            };
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
