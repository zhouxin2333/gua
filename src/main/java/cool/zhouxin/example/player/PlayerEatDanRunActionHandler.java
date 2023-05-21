package cool.zhouxin.example.player;

import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.handler.RunInstantActionHandler;
import cool.zhouxin.example.action.EatAction;
import cool.zhouxin.example.object.Dan;
import cool.zhouxin.example.object.Prop;

import java.util.Objects;

public class PlayerEatDanRunActionHandler implements RunInstantActionHandler<Player, EatAction, Dan> {
    @Override
    public Reaction invoke(Player player, EatAction action, Dan dan) {

        Prop prop = player.getBag().get(dan.getId());
        if (Objects.isNull(prop)) {
            System.out.println("道具" + dan.getName() + "不存在，无法使用");
            return CommonReaction.ok();
        }
        if (dan.getType().equals(Dan.Type.POWER)) {
            System.out.println("食用" + dan.getName() + "增长力量" + dan.getValue() + "kg");
            player.setStrength(player.getStrength() + dan.getValue());
        }
        player.getBag().del(dan.getId());
        return CommonReaction.ok();
    }
}
