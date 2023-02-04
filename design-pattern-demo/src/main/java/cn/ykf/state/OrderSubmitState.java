package cn.ykf.state;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class OrderSubmitState implements OrderState{

    @Override
    public void handle(Context ctx) {
        // 执行发货
        System.out.println("订单开始发货!");
        ctx.setState(new OrderDeliveryState());
    }

    @Override
    public String currentStateName() {
        return "订单已提交";
    }
}
