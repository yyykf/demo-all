package cn.ykf.jdk.utils;

import com.google.common.io.Files;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 读取单词工具类
 *
 * @author YuKaiFan
 * @date 2021/12/8
 */
public class WordsUtil {

    private static final String FILE_NAME = "words.txt";

    /**
     * 读取单词文件，默认从类路径下读取 words.txt 文件
     *
     * @return 单词集合
     */
    public static Set<String> loadWordsFile() {
        return loadWordsFile(FILE_NAME);
    }

    /**
     * 读取单词文件
     *
     * @param path 文件路径
     * @return 单词集合
     */
    public static Set<String> loadWordsFile(String path) {
        try {
            URI uri = ClassLoader.getSystemResource(path).toURI();
            return Files.readLines(Paths.get(uri).toFile(), StandardCharsets.UTF_8).stream().map(line -> {
                String[] split = line.split("\t");
                return split[1];
            }).collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return Collections.emptySet();
    }
}
