package com.example.demo.hazelcast.ex.map.lock;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

/**
 * 두 개 이상의 인스턴스에서 실행하면 레이스컨디션 발생 가능
 * 낙관적 잠금
 * 읽기 전용 시스템에 좋다.
 * 비관적 잠금보다 성능이 좋다.
 */
public class OptimisticMember {

    public static void main(String[] args) throws Exception {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, Value> map = hz.getMap("map");
        String key = "1";
        map.put(key, new Value());
        System.out.println("Starting");
        for (int k = 0; k < 1000; k++) {
            if (k % 10 == 0) System.out.println("At: " + k);
            for (; ; ) {
                Value oldValue = map.get(key);
                Value newValue = new Value(oldValue);
                Thread.sleep(10);
                newValue.amount++;
                if (map.replace(key, oldValue, newValue))
                    break;
            }
        }
        System.out.println("Finished! Result = " + map.get(key).amount);
    }

    static class Value implements Serializable {
        public int amount;

        public Value() {
        }

        public Value(Value that) {
            this.amount = that.amount;
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Value)) return false;
            Value that = (Value) o;
            return that.amount == this.amount;
        }
    }
}
