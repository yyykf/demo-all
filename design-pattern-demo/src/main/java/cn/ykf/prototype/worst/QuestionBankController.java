package cn.ykf.prototype.worst;

import cn.ykf.prototype.common.ChoiceQuestion;
import cn.ykf.prototype.common.EssayQuestion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class QuestionBankController {

    /**
     * 生成试卷
     *
     * @param candidate 学生
     * @param sno       学号
     * @return 试卷
     */
    public String createPaper(String candidate, String sno) {
        List<ChoiceQuestion> choiceQuestions = new ArrayList<>();

        // 这部分数据实际生产中可能来自于DB、缓存
        choiceQuestions.add(new ChoiceQuestion("JAVA所定义的版本中不包括", "D", new HashMap<String, String>(4) {{
            put("A", "JAVA2 EE");
            put("B", "JAVA2 Card");
            put("C", "JAVA2 ME");
            put("D", "JAVA2 HE");
            put("E", "JAVA2 SE");
        }}));

        choiceQuestions.add(new ChoiceQuestion("下列说法正确的是", "A", new HashMap<String, String>(4) {{
            put("A", "JAVA程序的main方法必须写在类里面");
            put("B", "JAVA程序中可以有多个main方法");
            put("C", "JAVA程序中类名必须与文件名一样");
            put("D", "JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来");
        }}));
        choiceQuestions.add(new ChoiceQuestion("变量命名规范说法正确的是", "B", new HashMap<String, String>() {{
            put("A", "变量由字母、下划线、数字、$符号随意组成；");
            put("B", "变量不能以数字作为开头；");
            put("C", "A和a在java中是同一个变量；");
            put("D", "不同类型的变量，可以起相同的名字；");
        }}));
        choiceQuestions.add(new ChoiceQuestion("以下()不是合法的标识符", "D", new HashMap<String, String>(4) {{
            put("A", "STRING");
            put("B", "x3x;");
            put("C", "void");
            put("D", "de$f");
        }}));
        choiceQuestions.add(new ChoiceQuestion("表达式(11+3*8)/4%3的值是", "D", new HashMap<String, String>(4) {{
            put("A", "31");
            put("B", "0");
            put("C", "1");
            put("D", "2");
        }}));

        List<EssayQuestion> essayQuestions = new ArrayList<>();
        essayQuestions.add(new EssayQuestion("小红马和小黑马生的小马几条腿", "4条腿"));
        essayQuestions.add(new EssayQuestion("铁棒打头疼还是木棒打头疼", "头最疼"));
        essayQuestions.add(new EssayQuestion("什么床不能睡觉", "牙床"));
        essayQuestions.add(new EssayQuestion("为什么好马不吃回头草", "后面的草没了"));

        // 输出结果
        StringBuilder detail = new StringBuilder("考生：" + candidate + "\r\n" +
                "考号：" + sno + "\r\n" +
                "--------------------------------------------\r\n" +
                "一、选择题" + "\r\n\n");

        for (int idx = 0; idx < choiceQuestions.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(choiceQuestions.get(idx).getQuestion()).append("\r\n");
            Map<String, String> option = choiceQuestions.get(idx).getOption();
            for (String key : option.keySet()) {
                detail.append(key).append("：").append(option.get(key)).append("\r\n");
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

}
