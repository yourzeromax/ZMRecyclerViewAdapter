package com.yourzeromax.zmrecyclerviewadapter.model;

/* *
 * Created by yourzeromax
 * on 2018/12/29
 *
 *
 */

public class Student {
    private String name;
    private String nickname;
    private int id;
    private int age;
    private String avatar;

    private boolean multi;

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public Student(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
