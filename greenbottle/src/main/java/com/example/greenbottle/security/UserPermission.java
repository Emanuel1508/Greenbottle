package com.example.greenbottle.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserPermission {

    CUSTOMER_READ("customer:read"),
    CUSTOMER_REGISTER("customer:register"),
    CUSTOMER_DELETE("customer:delete"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_BUY("customer:buy"),
    CUSTOMER_SELL("customer:sell");


    private final String permission;

    public String getPermission() {
        return this.permission;
    }


}
