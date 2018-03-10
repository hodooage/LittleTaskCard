package com.z.ho.littletaskcard.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class My extends DataSupport{
    private String  myName;
    private int myHeart;

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public int getMyHeart() {
        return myHeart;
    }

    public void setMyHeart(int myHeart) {
        this.myHeart = myHeart;
    }
}
