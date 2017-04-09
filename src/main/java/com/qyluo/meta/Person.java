package com.qyluo.meta;

/**
 * Created by qy_lu on 2017/4/4.
 */
public class Person {
    private String username;
    private String password;
    private String nickname;
    private int usertype;

    public Person() {
    }

    public Person(String username, String password, String nickname, int usertype) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
