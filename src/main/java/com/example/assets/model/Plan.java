package com.example.assets.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/14.
 * 计划表
 */

public class Plan extends DataSupport{
    private int id;
    private String aim;//计划的目标
    private double money;//计划存款金额
    private Date endTime;//截止时间
    private String beihzu;//备注

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBeihzu() {
        return beihzu;
    }

    public void setBeihzu(String beihzu) {
        this.beihzu = beihzu;
    }
}
