package cool.zhouxin.example.player;

import cool.zhouxin.base.AbstractElement;
import cool.zhouxin.example.object.Furniture;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player extends AbstractElement {

    private Furniture furniture;

    private int strength = 10;

    private Bag bag = new Bag();

    @Override
    public String toString() {
        return "玩家名：" + this.getName() + ", 力量：" + this.getStrength() + "kg";
    }

    private static final AtomicInteger index = new AtomicInteger(1);

    public Player() {
        this.setName("玩家" + index.getAndIncrement());
    }
}
