package com.z.ho.littletaskcard.entity;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class Aim {
    public String aimName;
    private int aimImageId;

    public Aim(String aimName,int aimImageId){
        this.aimName=aimName;
        this.aimImageId=aimImageId;
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
}
