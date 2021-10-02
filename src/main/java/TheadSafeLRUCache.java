import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import static java.lang.System.currentTimeMillis;

public class TheadSafeLRUCache<K> {
    private final Map<K, String> values = new ConcurrentHashMap<>();
    private final Queue<TimeValue<K>> times = new PriorityBlockingQueue<>();
    private final int size;

    public TheadSafeLRUCache(int size) {
        this.size = size;
    }


    public String doAction(K key){
        return key.toString();
    }

    public synchronized String doActionWithCache(K key){
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
