package cool.zhouxin.example.player;

import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.example.action.Actions;
import cool.zhouxin.example.action.PutDownAction;
import cool.zhouxin.example.object.Furniture;

public class PlayerPutDownFurnitureRunActionHandler extends AbstractPlayerFurnitureRunActionHandler<PutDownAction> {

    @Override
    public Reaction doInvoke(Player player, PutDownAction putDownAction, Furniture furniture) {

        if (!player.getFurniture().getName().equals(furniture.getName())) {
            System.out.println("你手里没有" + furniture.getName() + ", 怎么能放下");
            return CommonReaction.ok();
        }

        System.out.println(player.getName() + putDownAction.getName() + furniture.getName());
        player.setFurniture(null);
        player.addAction(Actions.getMutualExclusion(putDownAction));
        player.removeAction(putDownAction);

        return CommonReaction.ok();
    }
}
