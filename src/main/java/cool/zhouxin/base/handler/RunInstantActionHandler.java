package cool.zhouxin.base.handler;

import cool.zhouxin.base.Action;
import cool.zhouxin.base.Element;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.RefectionUtils;

import java.lang.reflect.Type;

public interface RunInstantActionHandler<T extends Element, R extends Action, Q extends Element>  {

    default String type() {
        Type[] actualTypeArguments = RefectionUtils.getActualTypeArguments(this.getClass(), RunInstantActionHandler.class);
        return buildType(Class.class.cast(actualTypeArguments[0]),
                Class.class.cast(actualTypeArguments[1]),
                Class.class.cast(actualTypeArguments[2]));
    }

    Reaction invoke(T origin, R action, Q target);

    static String buildType(Class<? extends Element> originClass, Class<? extends Action> actionClass,
                            Class<? extends Element> targetClass) {
        return originClass.getSimpleName() +
                "-" + actionClass.getSimpleName() +
                "-" + targetClass.getSimpleName();
    }

    static String buildType(Element origin, Action action, Element target) {
        return buildType(origin.getClass(), action.getClass(), target.getClass());
    }
}
