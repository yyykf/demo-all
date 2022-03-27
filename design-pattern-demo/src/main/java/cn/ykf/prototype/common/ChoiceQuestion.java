package cn.ykf.prototype.common;

import java.util.Map;

/**
 * 选择题
 *
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class ChoiceQuestion {

    /** 问题 */
    private String question;
    /** 答案 */
    private String answer;
    /** 选项 */
    private Map<String, String> option;

    public ChoiceQuestion() {
    }

    public ChoiceQuestion(String question, String answer, Map<String, String> option) {
        this.question = question;
        this.answer = answer;
        this.option = option;
    }

    public String getQuestion() {
        return question;
    }

    public ChoiceQuestion setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public ChoiceQuestion setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public Map<String, String> getOption() {
        return option;
    }

    public ChoiceQuestion setOption(Map<String, String> option) {
        this.option = option;
        return this;
    }
}
