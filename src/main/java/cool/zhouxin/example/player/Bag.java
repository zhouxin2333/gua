package cool.zhouxin.example.player;

import cool.zhouxin.example.object.Prop;

import java.util.HashMap;
import java.util.Map;

/**
 * 背包
 */
public class Bag {

    private static final Map<String, Prop> ID_PROP_CACHE = new HashMap<>();

    public <T extends Prop> T get(String id) {
        return (T) ID_PROP_CACHE.get(id);
    }

    public void del(String id) {
        ID_PROP_CACHE.remove(id);
    }

    public <T extends Prop> void add(T prop) {
        ID_PROP_CACHE.put(prop.getId(), prop);
    }

    public Map<String, Prop> getAll() {
        return ID_PROP_CACHE;
    }
}
