package cool.zhouxin.base;

public class CommonReaction implements Reaction{

    private static final CommonReaction OK = new CommonReaction();

    public static Reaction ok() {
        return OK;
    }
}
