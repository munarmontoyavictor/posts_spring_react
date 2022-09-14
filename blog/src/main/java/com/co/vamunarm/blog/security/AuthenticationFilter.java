package com.co.vamunarm.blog.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.co.vamunarm.blog.SpringApplicationContext;
import com.co.vamunarm.blog.models.requests.UserLoginRequestModel;
import com.co.vamunarm.blog.services.IUserService;
import com.co.vamunarm.blog.shared.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  // viene del framwork springf
  private final AuthenticationManager authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  // attempt intento, aqui se hace el login
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException {

      try {
          UserLoginRequestModel userModel = new ObjectMapper().readValue(request.getInputStream(),
                  UserLoginRequestModel.class);

          return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userModel.getEmail(),
                  userModel.getPassword(), new ArrayList<>()));
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

  }

  @Override
  public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
          Authentication authentication) throws IOException, ServletException {
      String username = ((User) authentication.getPrincipal()).getUsername();

      String token = Jwts.builder().setSubject(username)
              .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE))
              .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
      // header id publico
      IUserService userService = (IUserService)SpringApplicationContext.getBean("userService");
      UserDto userDto = userService.getUser(username);
      response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
      response.addHeader("UserId", userDto.getUserId());
  }

}
