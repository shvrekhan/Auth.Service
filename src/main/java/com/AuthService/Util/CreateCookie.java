package com.AuthService.Util;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CreateCookie {

    public Cookie createCookie(String cookieName, String cookieValue, int setMaxAge, boolean setHttpOnly, String path) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        if (setMaxAge > 0) {
            cookie.setMaxAge(setMaxAge);
        }
        cookie.setHttpOnly(setHttpOnly);
        if (path != null) {
            cookie.setPath(path);
        }
        return cookie;
    }
}
