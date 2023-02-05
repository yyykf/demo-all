package cn.ykf.adapter;

import cn.ykf.adapter.sdk.AlipaySdk;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class AliPaymentAdapter implements Payment {

    private AlipaySdk sdk = new AlipaySdk();

    @Override
    public void pay(String payOrderId) {
        // 委托给SDK
        sdk.alipay(payOrderId);
    }
}
