package cool.zhouxin.example.player;

import cool.zhouxin.base.Action;
import cool.zhouxin.base.CommonReaction;
import cool.zhouxin.base.Reaction;
import cool.zhouxin.base.handler.RunInstantActionHandler;
import cool.zhouxin.example.object.Furniture;

public abstract class AbstractPlayerFurnitureRunActionHandler<T extends Action> implements RunInstantActionHandler<Player, T, Furniture> {

    @Override
    public Reaction invoke(Player player, T action, Furniture furniture) {
        if (!player.containAction(action)) {
            System.out.println("你还不能" + action.getName());
            return CommonReaction.ok();
        }

        if (player.getStrength() < furniture.getWeight()) {
            System.out.println("你力量才" + player.getStrength() + "kg，怎么拿得起一个重" + furniture.getWeight() + "kg的" + furniture.getName());
            return CommonReaction.ok();
        }

        Reaction reaction = this.doInvoke(player, action, furniture);
        return reaction;
    }

    abstract Reaction doInvoke(Player player, T action, Furniture furniture);
}
