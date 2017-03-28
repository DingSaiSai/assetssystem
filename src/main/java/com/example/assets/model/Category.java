package com.example.assets.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 * 收支分类表（交通、餐费、工资，红包。。。）
 */

public class Category extends DataSupport{
    private int id;
    private int image;
    private String name;

    //Category和IncomeAndOutcome是多对一的关系
    List<IncomeAndOutcome> incomeAndOutcomeList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IncomeAndOutcome> getIncomeAndOutcomeList() {
        return incomeAndOutcomeList;
    }

    public void setIncomeAndOutcomeList(List<IncomeAndOutcome> incomeAndOutcomeList) {
        this.incomeAndOutcomeList = incomeAndOutcomeList;
    }
}
