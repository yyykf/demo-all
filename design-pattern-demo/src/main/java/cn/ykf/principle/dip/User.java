package cn.ykf.principle.dip;

/**
 * @author YuKaiFan
 * @date 2022/3/22
 */
public class User {

    /** 姓名 */
    private String name;
    /** 权重 */
    private Integer weight;

    public User() {
    }

    public User(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public User setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
