package cn.ykf.principle.lsp;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 信用卡
 *
 * @author YuKaiFan
 * @date 2022/3/21
 */
public class CreditCard extends CashCard {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCard.class);

    public CreditCard(String cardNo) {
        super(cardNo);
    }
    boolean rule2(BigDecimal amount) {
        return amount.compareTo(new BigDecimal(1000)) <= 0;
    }

    /**
     * 提现，信用卡贷款
     *
     * @param orderId 单号
     * @param amount  金额
     * @return 状态码
     */
    public String loan(String orderId, BigDecimal amount) {
        boolean rule = rule2(amount);
        if (!rule) {
            LOGGER.info("生成贷款单失败，金额超限。单号：{} 金额：{}", orderId, amount);
            return "0001";
        }
        // 模拟生成贷款单
        LOGGER.info("生成贷款单，单号：{} 金额：{}", orderId, amount);
        // 模拟支付成功
        LOGGER.info("贷款成功，单号：{} 金额：{}", orderId, amount);
        return super.negative(orderId, amount);

    }

    /**
     * 还款，信用卡还款
     *
     * @param orderId 单号
     * @param amount  金额
     * @return 状态码
     */
    public String repayment(String orderId, BigDecimal amount) {
        // 模拟生成还款单
        LOGGER.info("生成还款单，单号：{} 金额：{}", orderId, amount);
        // 模拟还款成功
        LOGGER.info("还款成功，单号：{} 金额：{}", orderId, amount);
        return super.positive(orderId, amount);
    }
}
