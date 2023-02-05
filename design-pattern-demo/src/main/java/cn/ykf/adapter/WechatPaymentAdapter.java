package cn.ykf.adapter;

import cn.ykf.adapter.sdk.AlipaySdk;
import cn.ykf.adapter.sdk.WxSdk;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class WechatPaymentAdapter implements Payment {

    private WxSdk sdk = new WxSdk();

    @Override
    public void pay(String payOrderId) {
        // 委托给SDK
        sdk.wxpay(payOrderId);
    }
}
