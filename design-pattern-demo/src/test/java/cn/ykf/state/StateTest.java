package cn.ykf.state;

import org.junit.jupiter.api.Test;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class StateTest {

    @Test
    void testOrderState(){
        // 订单预提交
        Context context = new Context(new OrderPreSubmitState());

        while (context.request()) {
        }

    }

}
