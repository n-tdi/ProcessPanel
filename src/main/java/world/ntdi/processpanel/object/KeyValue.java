package world.ntdi.processpanel.object;

public class KeyValue<K, V> {
    public final K key;
    public final V value;

    public KeyValue(K k, V v) {
        this.key = k;
        this.value = v;
    }
}
