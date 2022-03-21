package cn.ykf.principle.lsp;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 银行卡
 *
 * @author YuKaiFan
 * @date 2022/3/21
 */
public abstract class BankCard {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankCard.class);

    /** 卡号 */
    private String cardNo;

    private BankCard() {
    }

    public BankCard(String cardNo) {
        this.cardNo = cardNo;
    }

    abstract boolean rule(BigDecimal amount);

    /** 入金 */
    public String positive(String orderId, BigDecimal amount) {
        // 入款成功，存款、还款
        LOGGER.info("卡号{} 入款成功，单号：{} 金额：{}", cardNo, orderId, amount);
        return "0000";
    }

    /** 出金 */
    public String negative(String orderId, BigDecimal amount) {
        // 入款成功，存款、还款
        LOGGER.info("卡号{} 出款成功，单号：{} 金额：{}", cardNo, orderId, amount);
        return "0000";
    }
}
