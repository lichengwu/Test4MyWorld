package cn.lichengwu.test.jdk8;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-22 上午10:11
 */
public class Person {
    public Person() {}
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    private String name;
    private Integer age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String toString() {
        return "Person{name=" + name + "age=" + age + "}";
    }
}
