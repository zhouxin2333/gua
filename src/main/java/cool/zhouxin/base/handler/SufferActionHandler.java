package cool.zhouxin.base.handler;

import cool.zhouxin.base.Action;
import cool.zhouxin.base.Element;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.RefectionUtils;

import java.lang.reflect.Type;

public interface SufferActionHandler<T extends Element, R extends Action> {

    default String type() {
        Type[] actualTypeArguments = RefectionUtils.getActualTypeArguments(this.getClass(), SufferActionHandler.class);
        return buildType(Class.class.cast(actualTypeArguments[0]),
                Class.class.cast(actualTypeArguments[1]));
    }

    Reaction invoke(T element, R action);

    static String buildType(Class<? extends Element> elementClass, Class<? extends Action> actionClass) {
        return elementClass.getSimpleName() +
                "-" + actionClass.getSimpleName();
    }

    static String buildType(Element element, Action action) {
        return buildType(element.getClass(), action.getClass());
    }
}
