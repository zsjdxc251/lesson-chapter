package com.chapter.distributed.kafka.sample;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author zhengshijun
 * @version created on 2018/7/16.
 */
public class CustomPartitioner implements Partitioner {


    private static final Logger log = LoggerFactory.getLogger(CustomPartitioner.class);

    private final Random random = new Random();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        // 获取分区列表
        List<PartitionInfo> partitionInfoList = cluster.availablePartitionsForTopic(topic);
        int size = partitionInfoList.size();
        log.info("size:{}",size);
        int index = 0;
        if (key == null){
            index = random.nextInt(size);
        } else {
            index = Math.abs(key.hashCode()%size);
        }
        log.info("index:{}",index);
        return index;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("configs:{}",configs);

    }
}
