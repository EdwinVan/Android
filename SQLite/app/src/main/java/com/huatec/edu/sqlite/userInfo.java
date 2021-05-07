package com.huatec.edu.sqlite;

public class userInfo {
    private int id;
    private String username;
    private String password;
    private String sex;
    private int age;

    public userInfo(int id,String username, String password, String sex, int age) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassWord() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "userInfo{" + "id=" + id + ", username='" + username + '\'' +
                ", password='" + password + '\'' + ", sex='" + sex + '\'' +
                ", age=" + age + '}';
    }
}

