import static java.lang.Math.abs;

public class TimeValue<K> implements Comparable<TimeValue<K>> {
    private final long time;
    private final K key;

    public TimeValue(long time, K key) {
        this.time = time;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    @Override
    public int compareTo(TimeValue<K> o) {
        if (time == o.time) return 0;

        return (int) ((time - o.time) / abs(time - o.time));
    }
}

