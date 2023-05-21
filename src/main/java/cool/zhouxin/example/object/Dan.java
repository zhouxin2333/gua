package cool.zhouxin.example.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 丹药
 */
@Data
public class Dan extends AbstractProp<Dan> {

    private Type type;
    private int value;

    public Dan(Type type, int value) {
        super();
        this.type = type;
        this.value = value;
        this.setName(this.type.getDesc());
    }

    @Override
    public String toString() {
        return "丹药：" + this.getName() + ", id: " + this.getId() + ", 增强：" + this.getValue();
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        POWER("力量丹"),
        SPEED("速度丹"),

        ;

        private String desc;
    }
}
