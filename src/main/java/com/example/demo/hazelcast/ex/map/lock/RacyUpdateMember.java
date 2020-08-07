package com.example.demo.hazelcast.ex.map.lock;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

/**
 * 두 개 이상의 인스턴스에서 실행하면 레이스컨디션 발생 가능
 * 비관적 잠금
 * 동일한 키에 많은 업데이트가 있는 경우 비관적 잠금이 좋다.
 * 데이터 일관성의 관점에서 낙관적 잠금보다 강력.
 */
public class RacyUpdateMember {

    public static void main(String[] args) throws Exception {
        System.out.println(new Value().amount);

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, Value> map = hz.getMap("map");
        String key = "1";
        map.put(key, new Value());
        System.out.println("Starting");
        for (int k = 0; k < 1000; k++) {
            if (k % 100 == 0) System.out.println("At: " + k);

            map.lock(key);
            try {
                Value value = map.get(key);
                Thread.sleep(10);
                value.amount++;
                map.put(key, value);
            } finally {
                map.unlock(key);
            }

        }
        System.out.println("Finished! Result = " + map.get(key).amount);
    }

    static class Value implements Serializable {
        public int amount;
    }
}
