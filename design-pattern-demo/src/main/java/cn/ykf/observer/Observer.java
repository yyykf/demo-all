package cn.ykf.observer;

/**
 * 观察者
 *
 * @author YuKaiFan
 * @date 2021/10/14
 */
public interface Observer {

    /**
     * 更新数据
     *
     * @param temperature 温度
     * @param humidity    湿度
     */
    void update(double temperature, double humidity);
}
