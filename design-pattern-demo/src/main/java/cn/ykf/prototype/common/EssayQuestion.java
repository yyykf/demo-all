package cn.ykf.prototype.common;

/**
 * 问答题
 *
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class EssayQuestion {

    /** 问题 */
    private String question;
    /** 答案 */
    private String answer;

    public EssayQuestion() {
    }

    public EssayQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public EssayQuestion setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public EssayQuestion setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
