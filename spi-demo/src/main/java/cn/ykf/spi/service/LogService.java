package cn.ykf.spi.service;

/**
 * 日志接口
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/12
 */
public interface LogService {

    /**
     * 记录日志
     *
     * @param info 待记录信息
     */
    void log(String info);
}
