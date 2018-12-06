package com.lesson.project.elasticsearch.spring.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhengshijun
 * @version created on 2018/12/4.
 */

@Component
@Data
public class ElasticsearchProperties {

    @Value("es.cluster.name")
    private String clusterName;

    @Value("es.host")
    private String host;

    @Value("es.port")
    private Integer port;


}
