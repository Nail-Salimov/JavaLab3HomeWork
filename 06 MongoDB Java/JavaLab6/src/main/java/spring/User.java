package spring;

import java.util.Map;

public class User {

    private String _id;
    private String name;
    private String surname;
    private Integer age;
    private String sex;

    private Auto auto;
    private City city;

    public User(String _id, String name, String surname, Integer age, String sex, Auto auto, City city) {
        this._id = _id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.auto = auto;
        this.city = city;
    }

    public User() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
