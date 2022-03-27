package cn.ykf.prototype;

import cn.ykf.prototype.worst.QuestionBankController;
import org.junit.jupiter.api.Test;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class PrototypeTest {

    @Test
    void bad() {
        final QuestionBankController questionBankController = new QuestionBankController();

        System.out.println(questionBankController.createPaper("张三", "10000"));
        System.out.println(questionBankController.createPaper("李四", "10001"));
        System.out.println(questionBankController.createPaper("王五", "10002"));
    }

    @Test
    void better() throws CloneNotSupportedException {
        final cn.ykf.prototype.better.QuestionBankController  questionBankController = new cn.ykf.prototype.better.QuestionBankController ();

        System.out.println(questionBankController.createPaper("张三", "10000"));
        System.out.println(questionBankController.createPaper("李四", "10001"));
        System.out.println(questionBankController.createPaper("王五", "10002"));
    }


}
