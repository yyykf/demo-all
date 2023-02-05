package cn.ykf.adapter.sdk;

/**
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class AlipaySdk {

    /**
     * 阿里的支付方法
     *
     * @param payNo 支付号
     */
    public void alipay(String payNo) {
        System.out.printf("支付宝，发起支付调用，支付号: %s%n.", payNo);
    }
}
