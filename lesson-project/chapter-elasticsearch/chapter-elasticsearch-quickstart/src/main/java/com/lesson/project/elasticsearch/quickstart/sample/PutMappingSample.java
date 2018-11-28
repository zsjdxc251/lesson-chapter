package com.lesson.project.elasticsearch.quickstart.sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lesson.project.elasticsearch.quickstart.TransportClientSupport;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/11/28.
 */
public class PutMappingSample implements SampleSupport {
    @Override
    public void request(TransportClient client) {
        PutMappingRequestBuilder requestBuilder =
                client.admin().indices().preparePutMapping("demo").setType("test");

//        Map<String,Object> root = Maps.newHashMap();
//
//        Map<String,Object> mappings = Maps.newHashMap();
//        mappings.put("name","keyword");
//        mappings.put("age","integer");

//        JSONObject jsonObject  = JSON.parseObject("\"man\":{\"properties\":{\"name\":{\"type\":\"text\"},\"country\":{\"type\":\"keyword\"},\"age\":{\"type\":\"integer\"},\"date\":{\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"}}}");
//
//        Map<String,Object> root = jsonObject.getInnerMap()
//        Map<String,Object> properties = Maps.newHashMap();

        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder();

            XContentBuilder mappings = xContentBuilder.startObject().startObject("test");

           mappings =  mappings.startObject("properties");
           // mappings.field("name","keyword");




            xContentBuilder = mappings.endObject().endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestBuilder.setSource(xContentBuilder);


        System.out.println(requestBuilder.execute().actionGet().isAcknowledged());
    }


    public static void main(String[] args){

        TransportClientSupport.start(new PutMappingSample());


    }

}
