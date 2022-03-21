package cn.ykf.principle.lsp;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YuKaiFan
 * @date 2022/3/21
 */
public class BankCardTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankCardTest.class);

    @Test
    void test() {
        LOGGER.info("里氏替换前，使用储蓄卡");
        CashCard bankCard = new CashCard("66666");
        bankCard.withdrawal("10000", BigDecimal.TEN);
        bankCard.recharge("10001", BigDecimal.TEN);

        LOGGER.info("里氏替换后，使用信用卡");
        bankCard = new CreditCard("666666");
        bankCard.withdrawal("10000", BigDecimal.TEN);
        bankCard.recharge("10001", BigDecimal.TEN);
    }

}
