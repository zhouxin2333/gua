package cool.zhouxin.example.object;

import cool.zhouxin.base.AbstractElement;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public abstract class AbstractProp<T extends Prop> extends AbstractElement implements Prop {

    private String id;

    private static final Map<Class, AtomicInteger> ID_CACHE = new HashMap<>();

    public AbstractProp() {
        Class tClass = this.getClass();
        AtomicInteger atomicInteger = ID_CACHE.get(tClass);
        if (Objects.isNull(atomicInteger)) {
            atomicInteger = new AtomicInteger(1);
            ID_CACHE.put(tClass, atomicInteger);
        }

        this.id = tClass.getSimpleName() + atomicInteger.getAndIncrement();
    }
}
