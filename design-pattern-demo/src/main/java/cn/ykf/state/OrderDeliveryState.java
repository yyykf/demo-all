package cn.ykf.state;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class OrderDeliveryState implements OrderState{

    @Override
    public void handle(Context ctx) {
        System.out.println("订单完成!");
        ctx.setState(new OrderFinishState());
    }

    @Override
    public String currentStateName() {
        return "订单已发货";
    }
}
