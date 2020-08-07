package com.example.demo.hazelcast.event;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.partition.MigrationListener;
import com.hazelcast.partition.MigrationState;
import com.hazelcast.partition.ReplicaMigrationEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClusterMigrationListener implements MigrationListener {

    @Override
    public void migrationStarted(MigrationState state) {
        System.out.println("Migration Started: " + state);
    }

    @Override
    public void migrationFinished(MigrationState state) {
        System.out.println("Migration Finished: " + state);
    }

    @Override
    public void replicaMigrationCompleted(ReplicaMigrationEvent event) {
        System.out.println("Replica Migration Completed: " + event);
    }

    @Override
    public void replicaMigrationFailed(ReplicaMigrationEvent event) {
        System.out.println("Replica Migration Failed: " + event);
    }
}
