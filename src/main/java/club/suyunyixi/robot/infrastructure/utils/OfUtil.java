package club.suyunyixi.robot.infrastructure.utils;

import java.util.*;

public class OfUtil {

    @SafeVarargs
    public static <T> List<T> ofList(T... vs) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, vs);
        return list;
    }

    @SafeVarargs
    public static <T> Set<T> ofSet(T... vs) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, vs);
        return set;
    }


    public static <K, V> Map<K, V> ofMap(K k1, V v1) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        return map;
    }


    public static <K, V> Map<K, V> ofMap(List<K> keys, List<V> vals) {
        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), vals.get(i));
        }
        return map;
    }
}
