package com.billmate.mybillmate.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    Authority(String authority){
        this.value = authority;
    }

    @Override
    public String getAuthority() {
        return value;
    }
}
