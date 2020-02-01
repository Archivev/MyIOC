package com.Archiver.entity;

/**
 * @Author: Archiver
 * @Description:
 * @Date: Created in 19:47 2020/1/31
 * @Modified By:
 */
public class Man {
    private People people;
    private String age;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "people=" + people +
                ", age='" + age + '\'' +
                '}';
    }
}
