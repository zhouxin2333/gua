package cool.zhouxin;

import cool.zhouxin.base.handler.ActionHandlers;
import cool.zhouxin.example.action.*;
import cool.zhouxin.example.harasser.Harasser;
import cool.zhouxin.example.harasser.HarasserAttackRunActionHandler;
import cool.zhouxin.example.object.Dan;
import cool.zhouxin.example.object.Furniture;
import cool.zhouxin.example.player.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        init();

        // 初始化玩家
        System.out.println("======初始化玩家");
        Player player = new Player();
        player.addAction(Actions.get(ActionConstants.PICK_UP));
        System.out.println(player);

        // 初始化家具
        System.out.println("======初始化家具");
        Furniture pen = new Furniture("笔");
        Furniture desk = new Furniture("桌子", 20);
        Furniture sofa = new Furniture("沙发", 40);
        System.out.println(pen);
        System.out.println(desk);

        // 初始化道具
        System.out.println("======初始化道具");
        Dan powerDan1 = new Dan(Dan.Type.POWER, 20);
        Dan powerDan2 = new Dan(Dan.Type.POWER, 30);
        System.out.println(powerDan1);
        System.out.println(powerDan2);

        int maxRandom = player.getStrength() + powerDan1.getValue() - desk.getWeight();

        // 执行动作
        printCurrent(player);

        // 拾起笔
        System.out.println("=======尝试拾起笔");
        player.runInstant(pen, Actions.get(ActionConstants.PICK_UP));
        printCurrent(player);

        // 拾起桌子，无法拾起，因为当前手中已经有笔
        System.out.println("=======尝试拾起桌子");
        player.runInstant(desk, Actions.get(ActionConstants.PICK_UP));

        // 放下笔
        System.out.println("=======尝试放下笔");
        player.runInstant(pen, Actions.get(ActionConstants.PUT_DOWN));
        // 拾起桌子，无法拾起，因为桌子太重了
        System.out.println("=======尝试拾起桌子");
        player.runInstant(desk, Actions.get(ActionConstants.PICK_UP));
        printCurrent(player);

        // 拾起道具：力量丹1
        System.out.println("=======尝试拾起力量丹1");
        player.runInstant(powerDan1, Actions.get(ActionConstants.PICK_UP));
        printCurrent(player);

        // 拾起道具：力量丹2
        System.out.println("=======尝试拾起力量丹2");
        player.runInstant(powerDan2, Actions.get(ActionConstants.PICK_UP));
        printCurrent(player);

        // 吃掉力量丹1
        System.out.println("=======尝试吃掉力量丹1");
        player.runInstant(powerDan1, Actions.get(ActionConstants.EAT));
        printCurrent(player);

        // 拾起桌子
        System.out.println("=======尝试拾起桌子");
        player.runInstant(desk, Actions.get(ActionConstants.PICK_UP));
        printCurrent(player);

        // 放下桌子
        System.out.println("=======尝试放下桌子");
        player.runInstant(desk, Actions.get(ActionConstants.PUT_DOWN));
        printCurrent(player);

        // 初始化骚扰者1
        System.out.println("=======初始化骚扰者1");
        Harasser harasser1 = new Harasser();

        int attack1 = harasser1.createAttack(maxRandom/2, maxRandom);
        AttackAction attackAction1 = new AttackAction(attack1);
        // 骚扰者1执行攻击
        System.out.println("=======骚扰者1执行攻击，伤害：" + attack1);
        harasser1.run(attackAction1);

        System.out.println("=======玩家1受到攻击，伤害：" + attack1);
        player.suffer(attackAction1);
        printCurrent(player);

        System.out.println("=======初始化骚扰者2");
        Harasser harasser2 = new Harasser();

        int attack2 = harasser2.createAttack(maxRandom/2, maxRandom);
        AttackAction attackAction2 = new AttackAction(attack2);
        // 骚扰者1执行攻击
        System.out.println("=======骚扰者1执行攻击，伤害：" + attack2);
        harasser2.run(attackAction2);

        System.out.println("=======玩家1受到攻击，伤害：" + attack2);
        player.suffer(attackAction2);
        printCurrent(player);

        // 拾起桌子
        System.out.println("=======尝试拾起桌子");
        player.runInstant(desk, Actions.get(ActionConstants.PICK_UP));
        printCurrent(player);

    }

    private static void init() {
        ActionHandlers.register(new PlayerPickUpFurnitureRunActionHandler());
        ActionHandlers.register(new PlayerPutDownFurnitureRunActionHandler());
        ActionHandlers.register(new PlayerPickUpDanRunActionHandler());
        ActionHandlers.register(new PlayerEatDanRunActionHandler());
        ActionHandlers.register(new PlayerAttackSufferActionHandler());

        ActionHandlers.register(new HarasserAttackRunActionHandler());

        PickUpAction pickUpAction = new PickUpAction();
        Actions.register(pickUpAction);

        PutDownAction putDownAction = new PutDownAction();
        Actions.register(putDownAction);
        Actions.registerMutualExclusion(pickUpAction, putDownAction);

        EatAction eatAction = new EatAction();
        Actions.register(eatAction);
    }

    private static void printCurrent(Player player) {
        Set<String> canRunActions = player.getCanRunActions();
        String canRunActionsStr = canRunActions.stream().collect(Collectors.joining(","));
        System.out.println();
        System.out.println(player.getName() + "当前可以执行：" + canRunActionsStr);
        System.out.println("力量：" + player.getStrength());
        System.out.println("手中家具有：" + Optional.ofNullable(player.getFurniture()).map(Furniture::getName).orElse("无"));
        System.out.println("背包中有：" + player.getBag().getAll());
        System.out.println();
    }
}
