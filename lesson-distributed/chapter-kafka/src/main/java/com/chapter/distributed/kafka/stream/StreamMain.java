package com.chapter.distributed.kafka.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class StreamMain {

    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application2");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "121.196.232.248:9092,121.196.232.248:9093,121.196.232.248:9094");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream("SendWords");
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(textLine -> {
                    return Arrays.asList(textLine.toLowerCase().split("\\W+"));
                })
                .groupBy((key, word) -> {
                    try {
                        System.out.println("key is =>"+Serdes.Integer().deserializer().deserialize("SendWords",key.getBytes()));
                    }catch (Exception e) {

                    }
                    System.out.println("value is =>" +word);
                    return word;
                })
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
        wordCounts.toStream().to("CountSendWordsNum", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }

}
