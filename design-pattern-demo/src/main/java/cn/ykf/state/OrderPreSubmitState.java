package cn.ykf.state;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class OrderPreSubmitState implements OrderState {

    @Override
    public void handle(Context ctx) {
        // 锁定资源
        System.out.println("订单资源已锁定！");
        // 更改上下文状态
        ctx.setState(new OrderSubmitState());
    }

    @Override
    public String currentStateName() {
        return "订单预提交";
    }
}
