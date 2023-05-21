package cool.zhouxin.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RefectionUtils {

    public static Type[] getActualTypeArguments(Class targetClass, Class targetInterface) {
        Type[] genericInterfaces = targetClass.getGenericInterfaces();
        Optional<Type> typeOptional = Arrays.stream(genericInterfaces)
                .filter(type -> ((ParameterizedType) type).getRawType().equals(targetInterface))
                .findFirst();
        if (typeOptional.isPresent()) {
            Type type = typeOptional.get();
            ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            return actualTypeArguments;
        }else {

            Type genericSuperclass = targetClass.getGenericSuperclass();
            ParameterizedType parameterizedType = ParameterizedType.class.cast(genericSuperclass);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

            Class superclass = targetClass.getSuperclass();
            if (Objects.isNull(actualTypeArguments)) {
                return getActualTypeArguments(superclass, targetInterface);
            }else {
                return getActualTypeArguments(superclass, targetInterface, actualTypeArguments);
            }
        }
    }

    private static Type[] getActualTypeArguments(Class targetClass, Class targetInterface, Type[] beforeActualTypeArguments) {

        TypeVariable[] typeParameters = targetClass.getTypeParameters();
        Map<String, Type> map = IntStream.iterate(0, i -> i + 1).limit(beforeActualTypeArguments.length).boxed()
                .collect(Collectors.toMap(i -> typeParameters[i].getName(), i -> beforeActualTypeArguments[i]));

        Type[] genericInterfaces = targetClass.getGenericInterfaces();
        Optional<Type> typeOptional = Arrays.stream(genericInterfaces)
                .filter(type -> ((ParameterizedType) type).getRawType().equals(targetInterface))
                .findFirst();
        if (typeOptional.isPresent()) {
            Type type = typeOptional.get();
            ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

            actualTypeArguments = Arrays.stream(actualTypeArguments)
                    .map(t -> map.getOrDefault(t.getTypeName(), t))
                    .toArray(Type[]::new);

            return actualTypeArguments;
        }else {
            Type genericSuperclass = targetClass.getGenericSuperclass();
            ParameterizedType parameterizedType = ParameterizedType.class.cast(genericSuperclass);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

            actualTypeArguments = Arrays.stream(actualTypeArguments)
                    .map(t -> map.getOrDefault(t.getTypeName(), t))
                    .toArray(Type[]::new);

            Class superclass = targetClass.getSuperclass();
            return getActualTypeArguments(superclass, targetInterface, actualTypeArguments);
        }
    }
}
