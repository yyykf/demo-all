package cn.ykf.adapter;

/**
 * 支付接口
 *
 * @author YuKaiFan
 * @date 2023/2/4
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public interface Payment {

    /**
     * 支付
     *
     * @param payOrderId
     */
    void pay(String payOrderId);
}
