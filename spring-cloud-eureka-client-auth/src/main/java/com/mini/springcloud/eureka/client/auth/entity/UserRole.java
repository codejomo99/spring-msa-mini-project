package com.mini.springcloud.eureka.client.auth.entity;

public enum UserRole {
    MANAGER(Authority.ADMIN), USER(Authority.USER);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_MANAGER";
        public static final String ADMIN = "ROLE_USER";
    }
}
