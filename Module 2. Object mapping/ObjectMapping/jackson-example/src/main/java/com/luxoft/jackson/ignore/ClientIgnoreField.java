package com.luxoft.jackson.ignore;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClientIgnoreField {

    private String name;
    @JsonIgnore
    private int expenses;
    private boolean vip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

}
