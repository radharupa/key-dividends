package com.keydividend.entity;


/**
 *
 * @author  rupau
 *
 */
public class TickerExecutive {

    private String title;
    private String name;
    private String pay;
    private String currencyPay;
    private String gender;
    private int yearBorn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getCurrencyPay() {
        return currencyPay;
    }

    public void setCurrencyPay(String currencyPay) {
        this.currencyPay = currencyPay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

}
