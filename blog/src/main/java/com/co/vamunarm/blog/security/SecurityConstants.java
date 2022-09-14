package com.co.vamunarm.blog.security;

import com.co.vamunarm.blog.SpringApplicationContext;

public class SecurityConstants {
  public static final long EXPIRATION_DATE = 864000000; // 10 dias
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/users";
  public static final String LOGIN_URL = "/users/login";
  public static String getTokenSecret() {
    AppProperties appProperties = (AppProperties)SpringApplicationContext.getBean("AppProperties");
    return appProperties.getTokenSecret();
  }
}