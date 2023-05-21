package cool.zhouxin.base.handler;

import java.util.HashMap;
import java.util.Map;

public class ActionHandlers {

    private static final Map<String, RunInstantActionHandler> RUN_INSTANT_ACTION_HANDLER_MAP = new HashMap<>();
    private static final Map<String, RunActionHandler> RUN_ACTION_HANDLER_MAP = new HashMap<>();
    private static final Map<String, SufferActionHandler> SUFFER_ACTION_HANDLER_MAP = new HashMap<>();


    public static RunInstantActionHandler getRunInstantActionHandler(String type) {
        return RUN_INSTANT_ACTION_HANDLER_MAP.get(type);
    }

    public static RunActionHandler getRunActionHandler(String type) {
        return RUN_ACTION_HANDLER_MAP.get(type);
    }

    public static SufferActionHandler getSufferActionHandler(String type) {
        return SUFFER_ACTION_HANDLER_MAP.get(type);
    }

    public static void register(RunInstantActionHandler runInstantActionHandler) {
        RUN_INSTANT_ACTION_HANDLER_MAP.put(runInstantActionHandler.type(), runInstantActionHandler);
    }

    public static void register(RunActionHandler runActionHandler) {
        RUN_ACTION_HANDLER_MAP.put(runActionHandler.type(), runActionHandler);
    }

    public static void register(SufferActionHandler sufferActionHandler) {
        SUFFER_ACTION_HANDLER_MAP.put(sufferActionHandler.type(), sufferActionHandler);
    }
}
