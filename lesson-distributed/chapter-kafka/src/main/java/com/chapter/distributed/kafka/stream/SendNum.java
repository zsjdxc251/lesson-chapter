package com.chapter.distributed.kafka.stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class SendNum {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "121.196.232.248:9092,121.196.232.248:9093,121.196.232.248:9094");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<Integer, String> producer = new KafkaProducer<>(props);
        Scanner scanner = new Scanner(System.in);
        int times = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.equals("Bye!")) {
                break;
            }
            times += 1;
            producer.send(new ProducerRecord<>("SendWords",times,line));
        }
        producer.close();
    }

}
