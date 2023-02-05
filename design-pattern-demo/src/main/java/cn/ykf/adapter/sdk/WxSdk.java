package cn.ykf.adapter.sdk;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class WxSdk {

    /**
     * 微信的支付方法
     *
     * @param payNo 支付号
     */
    public void wxpay(String payNo) {
        System.out.printf("微信，发起支付调用，支付号: %s%n.", payNo);
    }
}
