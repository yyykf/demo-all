package cn.ykf.filter;

/**
 * 过滤器抽象基类
 *
 * @author YuKaiFan
 * @date 2021/10/29
 */
public abstract class AbstractFilter implements Filter {

    private Filter next;

    @Override
    public void filterAll(Request request, Response response) {
        doFilter(request, response);
        if (next != null) {
            // 传递给下一个处理器
            next.filterAll(request, response);
        }
    }

    @Override
    public void filterOnce(Request request, Response response) {
        // 判断是否支持该请求
        if (isSupported(request)) {
            doFilter(request, response);
            return;
        }

        // 处理不了的话就往下找，直到有过滤器可以处理该请求
        if (next != null) {
            next.filterOnce(request, response);
        }
    }

    @Override
    public void addNext(Filter next) {
        this.next = next;
    }

    protected abstract void doFilter(Request request, Response response);
}
