package cool.zhouxin.example.harasser;

import cool.zhouxin.base.AbstractElement;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Harasser extends AbstractElement {

    private Integer value;

    private static final AtomicInteger index = new AtomicInteger(1);

    public Harasser() {
        this.setName("骚扰者" + index.getAndIncrement());
    }

    public int createAttack(int min, int max) {
        if (Objects.isNull(this.value)) {
            this.value = ThreadLocalRandom.current().nextInt(min, max);
        }
        return this.value;
    }
}
