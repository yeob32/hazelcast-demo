package com.example.demo.hazelcast.ex.reliableTopic;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * topic
 * 기본 주제의 경우 백업이 없으며 인스턴스가 손실되면 해당 인스턴스에 포함 된 모든 이벤트가 손실된다.
 *
 * reliable topic
 * 생성 된 이벤트의 백업이 있고 인스턴스 오류가 있는 경우 대부분 (또는 모든) 이벤트가 백업에 존재한.
 *
 */
public class PublisherMember {

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        Random random = new Random();
        ITopic<Long> topic = hz.getReliableTopic("sometopic"); // 자동으로 원형 큐 만듬
        long messageId = 0;

        while (true) {
            topic.publish(messageId);
            messageId++;
            System.out.println("Written: " + messageId);
            sleepMillis(random.nextInt(100));
        }
    }

    public static boolean sleepMillis(int millis) {
        try {
            MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }
}
