package com.sk.entity;

import java.io.Serializable;

public class UserTable implements Serializable{
    private String userName;

    private String passWord;

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getpassWord() {
        return passWord;
    }

    public void setpassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }
}