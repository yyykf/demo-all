package cn.ykf.filter;

/**
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/29
 */
public class ParamFilter extends AbstractFilter{

    @Override
    public boolean isSupported(Request request) {
        // 每个过滤器自行判断是否支持该请求
        return false;
    }

    @Override
    protected void doFilter(Request request, Response response) {
        System.out.println("参数过滤");
    }
}
