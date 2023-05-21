package cool.zhouxin.example.player;

import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.handler.RunInstantActionHandler;
import cool.zhouxin.example.action.PickUpAction;
import cool.zhouxin.example.object.Dan;

import java.util.Objects;

public class PlayerPickUpDanRunActionHandler implements RunInstantActionHandler<Player, PickUpAction, Dan> {
    @Override
    public Reaction invoke(Player player, PickUpAction action, Dan dan) {

        Dan danInBag = player.getBag().get(dan.getId());
        if (Objects.nonNull(danInBag)) {
            System.out.println("包里已经有了" + dan);
            return CommonReaction.ok();
        }

        System.out.println("收集到道具：" + dan.getName() + "(" + dan.getValue() + ")");
        player.getBag().add(dan);

        return CommonReaction.ok();
    }
}
