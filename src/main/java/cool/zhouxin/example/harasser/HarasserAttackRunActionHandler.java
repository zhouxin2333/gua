package cool.zhouxin.example.harasser;

import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.handler.RunActionHandler;
import cool.zhouxin.example.action.AttackAction;

public class HarasserAttackRunActionHandler implements RunActionHandler<Harasser, AttackAction> {

    @Override
    public Reaction invoke(Harasser harasser, AttackAction action) {
        System.out.println(harasser.getName() + "发动攻击，伤害为：" + action.getValue());
        return CommonReaction.ok();
    }
}
