package cn.ykf.adapter;

import org.junit.jupiter.api.Test;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class AdapterTest {

    @Test
    void test(){
        Payment payment = new AliPaymentAdapter();
        // Payment payment = new WechatPaymentAdapter();
        payment.pay("code4j");
    }

}
