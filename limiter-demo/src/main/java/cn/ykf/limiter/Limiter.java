package cn.ykf.limiter;

/**
 * 限流器
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/26
 * @link <a href="https://www.tianxiaobo.com/2019/05/18/%E7%AE%80%E6%9E%90%E9%99%90%E6%B5%81%E7%AE%97%E6%B3%95/>
 */
public interface Limiter {

    void acquire();
}
