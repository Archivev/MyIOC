package com.Archiver.entity;

/**
 * @Author: Archiver
 * @Description:
 * @Date: Created in 12:55 2020/1/31
 * @Modified By:
 */
public class User {

    private String userId;

    private People people;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", people=" + people +
                '}';
    }
}
