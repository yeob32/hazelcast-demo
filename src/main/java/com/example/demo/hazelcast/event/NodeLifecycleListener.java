package com.example.demo.hazelcast.event;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

public class NodeLifecycleListener implements LifecycleListener {

    @Override
    public void stateChanged(LifecycleEvent event) {
        System.err.println(event);
    }

    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        hazelcastInstance.getLifecycleService().addLifecycleListener(new NodeLifecycleListener());
    }
}