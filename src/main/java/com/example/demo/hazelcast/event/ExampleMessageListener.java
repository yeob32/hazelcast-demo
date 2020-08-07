package com.example.demo.hazelcast.event;

import com.example.demo.domain.myEvent.MyEvent;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleMessageListener implements MessageListener<MyEvent> {

    @Override
    public void onMessage(Message<MyEvent> message) {
        MyEvent myEvent = message.getMessageObject();
        System.out.println("Message received = " + myEvent.toString());
    }

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ITopic<MyEvent> topic = hz.getReliableTopic("sometopic"); // 자동으로 원형 큐 만듬

        topic.addMessageListener(new ExampleMessageListener());

        MyEvent myEvent = new MyEvent("someEvent");
        topic.publish(myEvent);
        System.out.println(myEvent);
    }
}