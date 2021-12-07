package cn.ykf.filter;

/**
 * @author YuKaiFan
 * @date 2021/10/29
 */
public class TokenFilter extends AbstractFilter{

    @Override
    protected void doFilter(Request request, Response response) {
        System.out.println("Token校验");
    }

    @Override
    public boolean isSupported(Request request) {
        return true;
    }
}
