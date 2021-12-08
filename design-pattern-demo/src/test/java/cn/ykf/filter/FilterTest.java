package cn.ykf.filter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 责任链模式demo
 *
 * @author YuKaiFan
 * @date 2021/10/29
 */
public class FilterTest {

    private static Filter filter;

    @BeforeAll
    static void buildFilterChain() {
        // 构建过滤器链，如果整合了Spring的话，直接注入到集合中更简单
        List<Filter> filters = Arrays.asList(new SecurityFilter(), new TokenFilter(), new ParamFilter());

        for (int i = 0; i < filters.size(); i++) {
            if (i == 0) {
                filter = filters.get(0);
                continue;
            }

            // 下一个过滤器
            Filter next = filters.get(i);
            // 当前过滤器
            Filter cur = filters.get(i - 1);
            // 添加下一个过滤器
            cur.addNext(next);
        }
    }

    @Test
    void testAll() {
        filter.filterAll(new Request(), new Response());
    }

    @Test
    void testOnce() {
        filter.filterOnce(new Request(), new Response());
    }
}
