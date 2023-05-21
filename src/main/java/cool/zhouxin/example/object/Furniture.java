package cool.zhouxin.example.object;

import cool.zhouxin.base.AbstractElement;
import lombok.Data;

/**
 * 家具
 */
@Data
public class Furniture extends AbstractElement implements Object {

    private int weight;

    public Furniture(String name) {
        this.setName(name);
    }

    public Furniture(String name, int weight) {
        this.setName(name);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "家具：" + this.getName() + "，重量：" + this.getWeight() + "kg";
    }
}
