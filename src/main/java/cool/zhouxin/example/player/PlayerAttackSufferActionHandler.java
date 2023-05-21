package cool.zhouxin.example.player;

import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.handler.SufferActionHandler;
import cool.zhouxin.example.action.AttackAction;

public class PlayerAttackSufferActionHandler implements SufferActionHandler<Player, AttackAction> {

    @Override
    public Reaction invoke(Player player, AttackAction action) {
        System.out.println(player.getName() + "受到攻击，力量减少：" + action.getValue());
        player.setStrength(player.getStrength() - action.getValue());
        return CommonReaction.ok();
    }
}
