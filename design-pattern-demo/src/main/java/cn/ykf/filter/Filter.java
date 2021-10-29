package cn.ykf.filter;

/**
 * 过滤器接口
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/29
 */
public interface Filter {

    /**
     * 过滤，所有过滤器都可以处理请求
     *
     * @param request  request
     * @param response response
     */
    void filterAll(Request request, Response response);

    /**
     * 过滤，一旦有过滤器可以处理请求，就不往下传递
     *
     * @param request  request
     * @param response response
     */
    void filterOnce(Request request, Response response);

    /**
     * 是否支持处理该请求
     *
     * @param request request
     * @return {@code true} - 支持，{@code false} - 不支持
     */
    boolean isSupported(Request request);

    /**
     * 添加下一个过滤器
     *
     * @param next 下一个处理器
     */
    void addNext(Filter next);
}
