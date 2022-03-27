package cn.ykf.prototype.better;

import cn.ykf.prototype.common.ChoiceQuestion;
import cn.ykf.prototype.common.EssayQuestion;
import cn.ykf.prototype.better.utils.QuestionRandomUtils;
import cn.ykf.prototype.better.utils.QuestionRandomUtils.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * 题库
 *
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class QuestionBank implements Cloneable {

    /** 考生 */
    private String candidate;
    /** 学号 */
    private String sno;
    /** 选择题 */
    private ArrayList<ChoiceQuestion> choiceQuestions = new ArrayList<>();
    /** 问答题 */
    private ArrayList<EssayQuestion> essayQuestions = new ArrayList<>();

    public QuestionBank append(ChoiceQuestion choiceQuestion) {
        this.choiceQuestions.add(choiceQuestion);
        return this;
    }

    public QuestionBank append(EssayQuestion essayQuestion) {
        this.essayQuestions.add(essayQuestion);
        return this;
    }

    @Override
    protected QuestionBank clone() throws CloneNotSupportedException {
        // 先拷贝一份题库
        QuestionBank questionBank = (QuestionBank) super.clone();
        // 因为要打乱题目、答案，因此需要拷贝题库中的内容
        questionBank.choiceQuestions = (ArrayList<ChoiceQuestion>) choiceQuestions.clone();
        questionBank.essayQuestions = (ArrayList<EssayQuestion>) essayQuestions.clone();

        // 题目乱序
        Collections.shuffle(choiceQuestions);
        Collections.shuffle(essayQuestions);

        // 答案乱序
        for (ChoiceQuestion choiceQuestion : questionBank.choiceQuestions) {
            final Pair pair = QuestionRandomUtils.random(choiceQuestion.getOption(), choiceQuestion.getAnswer());
            choiceQuestion.setOption(pair.getOption());
            choiceQuestion.setAnswer(pair.getAnswer());
        }

        return questionBank;
    }

    @Override
    public String toString() {
        StringBuilder detail = new StringBuilder("考生：" + candidate + "\r\n" +
                "考号：" + sno + "\r\n" +
                "--------------------------------------------\r\n" +
                "一、选择题" + "\r\n\n");

        for (int idx = 0; idx < choiceQuestions.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(choiceQuestions.get(idx).getQuestion()).append("\r\n");
            Map<String, String> option = choiceQuestions.get(idx).getOption();
            for (String key : option.keySet()) {
                detail.append(key).append("：").append(option.get(key)).append("\r\n");;
            }
            detail.append("答案：").append(choiceQuestions.get(idx).getAnswer()).append("\r\n\n");
        }

        detail.append("二、问答题" + "\r\n\n");

        for (int idx = 0; idx < essayQuestions.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(essayQuestions.get(idx).getQuestion()).append("\r\n");
            detail.append("答案：").append(essayQuestions.get(idx).getAnswer()).append("\r\n\n");
        }

        return detail.toString();
    }

    public QuestionBank setCandidate(String candidate) {
        this.candidate = candidate;
        return this;
    }

    public QuestionBank setSno(String sno) {
        this.sno = sno;
        return this;
    }
}
