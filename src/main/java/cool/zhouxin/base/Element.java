package cool.zhouxin.base;

/**
 * 元素
 */
public interface Element {

    /**
     * 元素名称
     * @return
     */
    String getName();

    /**
     * 元素执行对另外一个元素执行即时动作，然后产生某种反应
     * @param action
     * @return
     */
    Reaction runInstant(Element target, Action action);

    /**
     * 元素某个动作，是否命中另一个元素不确定，然后产生某种反应，比如弹道攻击
     * @param action
     * @return
     */
    Reaction run(Action action);

    /**
     * 某个元素受到了某个动作，然后产生某种反应，比如受到弹道攻击
     * @param action
     * @return
     */
    Reaction suffer(Action action);
}
