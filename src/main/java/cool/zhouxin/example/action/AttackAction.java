package cool.zhouxin.example.action;

import cool.zhouxin.base.Action;
import lombok.Data;

@Data
public class AttackAction implements Action {

    private int value;

    @Override
    public String getName() {
        return ActionConstants.ATTACK;
    }

    public AttackAction(int value) {
        this.value = value;
    }
}
