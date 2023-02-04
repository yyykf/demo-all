package cn.ykf.state;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class OrderFinishState implements OrderState{

    @Override
    public void handle(Context ctx) {
        // 推送通知
        System.out.println("推送通知提醒用户!");
        ctx.setState(null);
    }

    @Override
    public String currentStateName() {
        return "订单已完成";
    }
}
