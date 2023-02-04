package cn.ykf.state;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class Context {

    private OrderState state;

    public Context(OrderState state) {
        this.state = state;
    }

    /**
     * 执行状态处理
     */
    public boolean request(){
        if (state == null) {
            System.out.println("请先设置状态!");
            return false;
        }
        state.handle(this);
        return true;
    }

    public OrderState getState() {
        return state;
    }

    public Context setState(OrderState state) {
        if (state == null) {
            System.out.printf("状态结束，上一状态为: %s%n",this.state.currentStateName());
        }else{
            System.out.printf("状态变化，%s -> %s%n",this.state.currentStateName(), state.currentStateName());
        }
        this.state = state;
        return this;
    }
}
