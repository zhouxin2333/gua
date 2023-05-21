package cool.zhouxin.example.action;

import cool.zhouxin.base.Action;

import java.util.HashMap;
import java.util.Map;

public class Actions {

    private static final Map<String, Action> CACHE = new HashMap<>();
    private static final Map<String, Action> MUTUAL_EXCLUSION_CACHE = new HashMap<>();


    public static Action get(String name) {
        return CACHE.get(name);
    }

    public static Action getMutualExclusion(Action action) {
        return MUTUAL_EXCLUSION_CACHE.get(action.getName());
    }

    public static void register(Action action) {
        CACHE.put(action.getName(), action);
    }

    public static void registerMutualExclusion(Action action1, Action action2) {
        MUTUAL_EXCLUSION_CACHE.put(action2.getName(), action1);
        MUTUAL_EXCLUSION_CACHE.put(action1.getName(), action2);
    }
}
