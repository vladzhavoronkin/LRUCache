import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.System.currentTimeMillis;

public class LRUCache<K> {
    private final Map<K, String> values = new HashMap<>();
    private final Queue<TimeValue<K>> times = new PriorityQueue<>();
    private final int size;

    public LRUCache(int size) {
        this.size = size;
    }

    public String doAction(K key){
        return key.toString();
    }

    public String doActionWithCache(K key){
        long time = currentTimeMillis();
        if (values.containsKey(key)){
            times.add(new TimeValue<>(time, key));
            return values.get(key);
        }

        String result = doAction(key);

        if (values.size() == size){
            K last = times.remove().getKey();
            values.remove(last);
        }

        values.put(key, result);
        times.add(new TimeValue<>(time, key));

        return result;
    }
}