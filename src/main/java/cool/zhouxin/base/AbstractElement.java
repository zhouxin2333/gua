package cool.zhouxin.base;

import cool.zhouxin.base.handler.ActionHandlers;
import cool.zhouxin.base.handler.RunActionHandler;
import cool.zhouxin.base.handler.RunInstantActionHandler;
import cool.zhouxin.base.handler.SufferActionHandler;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public abstract class AbstractElement implements Element {

    private static final Set<String> AVAILABLE_ACTIONS = new HashSet<>();

    private String name;

    @Override
    public Reaction runInstant(Element target, Action action) {
        String type = RunInstantActionHandler.buildType(this, action, target);
        RunInstantActionHandler runInstantActionHandler = ActionHandlers.getRunInstantActionHandler(type);

        if (Objects.isNull(runInstantActionHandler)) return CommonReaction.ok();
        Reaction reaction = runInstantActionHandler.invoke(this, action, target);
        return reaction;
    }

    @Override
    public Reaction run(Action action) {
        String type = RunActionHandler.buildType(this, action);
        RunActionHandler runActionHandler = ActionHandlers.getRunActionHandler(type);

        if (Objects.isNull(runActionHandler)) return CommonReaction.ok();
        Reaction reaction = runActionHandler.invoke(this, action);
        return reaction;
    }

    @Override
    public Reaction suffer(Action action) {
        String type = SufferActionHandler.buildType(this, action);
        SufferActionHandler sufferActionHandler = ActionHandlers.getSufferActionHandler(type);

        if (Objects.isNull(sufferActionHandler)) return CommonReaction.ok();
        Reaction reaction = sufferActionHandler.invoke(this, action);
        return reaction;
    }

    public Set<String> getCanRunActions() {
        return AVAILABLE_ACTIONS;
    }

    public void addAction(Action action) {
        AVAILABLE_ACTIONS.add(action.getName());
    }

    public void removeAction(Action action) {
        AVAILABLE_ACTIONS.remove(action.getName());
    }

    public boolean containAction(Action action) {
        return AVAILABLE_ACTIONS.contains(action.getName());
    }
}
