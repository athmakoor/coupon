package com.coupon.auth.payload;

public class AuthResponse {
    private String access_token;

    public AuthResponse() {

    }

    public AuthResponse(final String accessToken) {
        this.access_token = accessToken;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
