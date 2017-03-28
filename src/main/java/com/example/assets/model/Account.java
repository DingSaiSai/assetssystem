package com.example.assets.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 * 账户表
 */

public class Account extends DataSupport{
    private int id;
    private String type;//账户的类型

    //Account表和IncomeAndOutcome表示多对一的关系
    List<IncomeAndOutcome> incomeAndOutcomeList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<IncomeAndOutcome> getIncomeAndOutcomeList() {
        return incomeAndOutcomeList;
    }

    public void setIncomeAndOutcomeList(List<IncomeAndOutcome> incomeAndOutcomeList) {
        this.incomeAndOutcomeList = incomeAndOutcomeList;
    }
}
