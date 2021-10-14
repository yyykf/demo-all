package cn.ykf.observer;

/**
 * 主题
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/14
 */
public interface Subject {

    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer 观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
