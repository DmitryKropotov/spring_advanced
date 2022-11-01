package com.luxoft.jackson.ignore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class ClientDtoIncludeNonDefault {

    private String name;
    private int expenses;
    private boolean vip;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(final int expenses) {
        this.expenses = expenses;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(final boolean vip) {
        this.vip = vip;
    }

}
