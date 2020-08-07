package com.example.demo.hazelcast;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Queue;

@RequiredArgsConstructor
@Service
public class HazelcastService {

    private final HazelcastInstance hazelcastInstance;

    public void setUp() {
        Map<Integer, String> mapCustomers = hazelcastInstance.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");

        System.out.println("Customer with key 1: " + mapCustomers.get(1));
        System.out.println("Map Size:" + mapCustomers.size());

        Queue<String> queueCustomers = hazelcastInstance.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Mary");
        queueCustomers.offer("Jane");

        System.out.println("First customer: " + queueCustomers.poll());
        System.out.println("Second customer: " + queueCustomers.peek());
        System.out.println("Queue size: " + queueCustomers.size());

        IQueue<String> q1 = hazelcastInstance.getQueue("q");
        q1.add("foo");

        System.out.println("q1 size : " + q1.size());

        Map<String, String> capitalcities = hazelcastInstance.getMap( "capitals" );
        capitalcities.put( "1", "Tokyo" );
        capitalcities.put( "2", "Paris" );
        capitalcities.put( "3", "Washington" );
        capitalcities.put( "4", "Ankara" );
        capitalcities.put( "5", "Brussels" );
        capitalcities.put( "6", "Amsterdam" );
        capitalcities.put( "7", "New Delhi" );
        capitalcities.put( "8", "London" );
        capitalcities.put( "9", "Berlin" );
        capitalcities.put( "10", "Oslo" );
        capitalcities.put( "11", "Moscow" );

        System.out.println("capitalcities size : " + capitalcities.size());
    }
}
