package com.co.vamunarm.blog.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
// middleware sirve validar token
// Procesa el token, filter midelware, verifica si token es valdo o no
//
public class AuthorizationFilter extends BasicAuthenticationFilter {

  public AuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }
 //FilterChain chain , la cadena de filtros
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = getHeaderToken(request);
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      // se pasa el request al siguente filtro
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authenticationToken = getAutentication(request);
    // le decimos al contesto de la aplicacion vamos a setiar el token
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAutentication(HttpServletRequest request) {
    String token = getHeaderToken(request);
    if (token != null) {
      token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
      //setSigningKey verifica si la clave del token es la misma que la clave con la que se firmo
      //
      String  user = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret())
      .parseClaimsJws(token).getBody()
      .getSubject(); // subject tiene el correo electronico
      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

      }
      return null;
    }
    return null;
  }

  private String getHeaderToken(HttpServletRequest request) {
    return request.getHeader(SecurityConstants.HEADER_STRING);
  }



}
