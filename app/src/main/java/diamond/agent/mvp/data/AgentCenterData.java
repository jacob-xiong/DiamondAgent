package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * Created by Jacob on 2018-9-14.
 */

public class AgentCenterData implements Serializable {
    private String userNum;
    private String userSuperior;
    private String todayAmount;
    private String monthAmount;
    private String allAmount;
    private String surplusDays;
    private String userBalance;
    private String renewPrice = String.valueOf(199);


    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserSuperior() {
        return userSuperior;
    }

    public void setUserSuperior(String userSuperior) {
        this.userSuperior = userSuperior;
    }

    public String getTodayAmount() {
        return todayAmount;
    }

    public void setTodayAmount(String todayAmount) {
        this.todayAmount = todayAmount;
    }

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(String monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(String allAmount) {
        this.allAmount = allAmount;
    }

    public String getSurplusDays() {
        return surplusDays;
    }

    public void setSurplusDays(String surplusDays) {
        this.surplusDays = surplusDays;
    }

    public String getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    public String getRenewPrice() {
        return renewPrice;
    }

    public void setRenewPrice(String renewPrice) {
        this.renewPrice = renewPrice;
    }
}
