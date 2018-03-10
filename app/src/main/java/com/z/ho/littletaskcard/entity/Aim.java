package com.z.ho.littletaskcard.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class Aim extends DataSupport {
    private String aimName;
    private int aimImageId;
    private int importance;
    private int state;

    public Aim(){

    }

    public Aim(String aimName,int aimImageId,int importance){
        this.aimName=aimName;
        this.aimImageId=aimImageId;
        this.importance=importance;
    }

    public String getAimName() {
        return aimName;
    }

    public void setAimName(String aimName) {
        this.aimName = aimName;
    }

    public int getAimImageId() {
        return aimImageId;
    }

    public void setAimImageId(int aimImageId) {
        this.aimImageId = aimImageId;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
