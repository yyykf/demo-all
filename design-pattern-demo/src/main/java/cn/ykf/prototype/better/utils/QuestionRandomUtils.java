package cn.ykf.prototype.better.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author YuKaiFan
 * @date 2022/3/27
 */
public class QuestionRandomUtils {

    public static Pair random(Map<String, String> options, String answer) {
        // 先获取当前的选项集合
        final Set<String> optionKeySet = options.keySet();
        final List<String> optionKeys = new ArrayList<>(optionKeySet);

        // 打乱选项
        Collections.shuffle(optionKeys);

        // 生成新的选项集合
        HashMap<String, String> newOptions = new HashMap<>(options.size());
        String newAnswer = "";

        // 遍历选项集合，为每个选项的答案生成一个新的 Key
        int idx = 0;
        for (String optionKey : optionKeySet) {
            final String newOptionKey = optionKeys.get(idx++);
            newOptions.put(newOptionKey, options.get(optionKey));

            // 保存新的答案
            if (optionKey.equals(answer)) {
                newAnswer = optionKey;
            }
        }

        return new Pair(newAnswer, newOptions);
    }

   public static class Pair{
        private String answer;
        private Map<String, String> option;

        public Pair() {
        }

        public Pair(String answer, Map<String, String> option) {
            this.answer = answer;
            this.option = option;
        }

        public String getAnswer() {
            return answer;
        }

        public Pair setAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Map<String, String> getOption() {
            return option;
        }

        public Pair setOption(Map<String, String> option) {
            this.option = option;
            return this;
        }
    }
}
