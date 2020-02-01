package com.Archiver.entity;

/**
 * @Author: Archiver
 * @Description:
 * @Date: Created in 12:51 2020/1/31
 * @Modified By:
 */
public class People {

    private String name;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
