package com.lesson;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 *
 * https://es.quanke.name/document-apis/bulk-api.html
 *
 */
public class App 
{
    public static void main( String[] args )

    throws Exception{

        Settings settings = Settings.builder()
                .put("cluster.name", "es-first").put("client.transport.sniff", true).build();  //设置ES实例的名称
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.112.139"), 9300));


//        RestHighLevelClient restclient = new RestHighLevelClient(RestClient.builder(
//                new HttpHost("192.168.112.139", 9200, "http")));



//        long index = 0;
//        while (!Thread.currentThread().isInterrupted()) {
//
//            Map<String, Object> json = new HashMap<String, Object>();
//            json.put("user","kimchy"+index);
//            json.put("postDate", DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
//            json.put("message","trying out Elasticsearch "+index);
//
//            IndexResponse response = client.prepareIndex("fendo", "fendodate")
//                    .setSource(json)
//                    .get();
//            System.out.println(response.getResult());
//
//            TimeUnit.SECONDS.sleep(200);
//            index ++;
//        }



//        Map<String, Object> json = new HashMap<>();
//        //json.put("_id","demo");
//        json.put("postDate","2018-01-30");
//        json.put("message","trying out Demo");
//
//        IndexResponse response = client.prepareIndex("demo", "test")
//
//                .setSource(json)
//                .get();
//
//
//        client.search(new SearchRequest());
//        System.out.println(response.getResult());


//        BulkRequestBuilder bulkRequest = client.prepareBulk();
//
//
//        bulkRequest.add(client.prepareIndex("people", "man", "3")
//                .setSource(JsonXContent.contentBuilder()
//                        .startObject()
//                        .field("name", "kimchy")
//                        .field("country", "china")
//                        .field("age", "40")
//                        .field("date", new Date().getTime())
//                        .endObject()
//                )
//        );
//        BulkResponse responses = bulkRequest.get();
//
//
//        if (responses.hasFailures()){
//            System.out.println(responses.buildFailureMessage());
//        }






//        long index = 5;
//        while (!Thread.currentThread().isInterrupted()) {
//
//            IndexRequestBuilder requestBuilder = client.prepareIndex("people", "man", String.valueOf(index))
//                .setSource(JsonXContent.contentBuilder()
//                        .startObject()
//                        .field("name", "kimchy"+index)
//                        .field("country", "china"+index)
//                        .field("age", String.valueOf(index+40))
//                        .field("date", new Date().getTime())
//                        .endObject()
//                );
//
//            IndexResponse indexResponse = requestBuilder.get();
//
//            System.out.println(indexResponse.getResult());
//
//            TimeUnit.MILLISECONDS.sleep(200);
//            index ++;
//        }



        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
       // searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("state").size(10));

        //searchSourceBuilder.query(QueryBuilders.matchQuery("name","kimchy*"));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("people");
        searchRequest.types("man");

       searchRequest.source(searchSourceBuilder);
        ActionFuture<SearchResponse> actionFuture = client.search(searchRequest);




       SearchResponse searchResponse = client.prepareSearch("people").setSize(200).get();

//        do {
//
//
////            searchResponse = client.prepareSearchScroll(searchResponse.getScrollId())
////                    .setScroll(new TimeValue(60000)).execute().actionGet();
//        } while (searchResponse.getHits().getHits().length != 0);

        for (SearchHit hit : searchResponse.getHits().getHits()) {

            System.out.println(hit.toString());
        }
        System.out.println(searchResponse.getHits().getHits().length);

        System.out.println(searchResponse.getScrollId());
        client.close();


    }
}
