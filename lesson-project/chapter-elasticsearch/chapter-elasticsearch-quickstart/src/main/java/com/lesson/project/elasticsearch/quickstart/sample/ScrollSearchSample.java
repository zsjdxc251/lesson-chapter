package com.lesson.project.elasticsearch.quickstart.sample;

import com.lesson.project.elasticsearch.quickstart.TransportClientSupport;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.ClearScrollRequestBuilder;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 *
 *    es 查询示例
 * @author zhengshijun
 * @version created on 2018/11/27.
 */
@Slf4j
public class ScrollSearchSample implements SampleSupport {
    @Override
    public void request(TransportClient client) {



        SearchResponse scrollResp = client.prepareSearch("people")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000)) //为了使用 scroll，初始搜索请求应该在查询中指定 scroll 参数，告诉 Elasticsearch 需要保持搜索的上下文环境多长时间（滚动时间）
                //.setQuery(QueryBuilders.termQuery("user", "kimchy"))                 // Query 查询条件
                .setSize(5).get(); //max of 100 hits will be returned for each scroll
        //Scroll until no hits are returned

        String scrollId = scrollResp.getScrollId();
        System.out.println(scrollId);
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...

                System.out.println("" + hit.getSourceAsString());
            }

            scrollResp = client.prepareSearchScroll(scrollId).setScroll(new TimeValue(60000)).execute().actionGet();
        }
        while (scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.





        clearScroll(client,scrollId);


    }


    /**
     *
     *
     *
     * @param client
     * @param scrollId
     */
    private void clearScroll(TransportClient client,String scrollId) {

        ClearScrollRequestBuilder requestBuilder = client.prepareClearScroll();
        requestBuilder.addScrollId(scrollId);
        ClearScrollResponse response = requestBuilder.get();

        if (response.isSucceeded()){
            log.info("成功清除");
        }





    }


    public static void main(String[] args){

        TransportClientSupport.start(new ScrollSearchSample());
    }
}
