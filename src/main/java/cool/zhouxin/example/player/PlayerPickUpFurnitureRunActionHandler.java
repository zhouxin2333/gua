package cool.zhouxin.example.player;

import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.example.action.Actions;
import cool.zhouxin.example.action.PickUpAction;
import cool.zhouxin.example.object.Furniture;

public class PlayerPickUpFurnitureRunActionHandler extends AbstractPlayerFurnitureRunActionHandler<PickUpAction> {

    @Override
    public Reaction doInvoke(Player player, PickUpAction pickUpAction, Furniture furniture) {

        System.out.println(player.getName() + pickUpAction.getName() + furniture.getName());
        player.setFurniture(furniture);
        player.addAction(Actions.getMutualExclusion(pickUpAction));
        player.removeAction(pickUpAction);

        return CommonReaction.ok();
    }
}
