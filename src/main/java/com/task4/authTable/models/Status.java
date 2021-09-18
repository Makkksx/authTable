package com.task4.authTable.models;

import org.springframework.security.core.GrantedAuthority;

public enum Status implements GrantedAuthority {
    ACTIVE, BANNED;

    @Override
    public String getAuthority() {
        return name();
    }
}
