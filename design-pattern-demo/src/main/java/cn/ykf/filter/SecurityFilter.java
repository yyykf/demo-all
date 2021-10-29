package cn.ykf.filter;

/**
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/29
 */
public class SecurityFilter extends AbstractFilter{

    @Override
    public boolean isSupported(Request request) {
        return false;
    }

    @Override
    protected void doFilter(Request request, Response response) {
        System.out.println("安全校验");
    }
}
