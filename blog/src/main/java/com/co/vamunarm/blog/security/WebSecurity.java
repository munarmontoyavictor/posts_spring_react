package com.co.vamunarm.blog.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.co.vamunarm.blog.services.IUserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
  private final IUserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  // tenemos los filtros
  public WebSecurity(IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeRequests()
    .antMatchers(HttpMethod.POST, "/users").permitAll()
    .antMatchers(HttpMethod.GET, "/posts/last").permitAll()
    .antMatchers(HttpMethod.GET, "/posts/{id}").permitAll()
    .anyRequest().authenticated()
    .and().addFilter(getAuthenticationFilter())
    .addFilter(new AuthorizationFilter(authenticationManager()))//verificar si el jwt es valido
    //manejar la secion, que no cree una sesion si no que autentique cada request
    .sessionManagement()
    //STATELESS cuando se establece uan coneccion entre cliente y el servidor no se genera una variable de session
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
  }

  // Cambiar la url por defecto sprint security
  public AuthenticationFilter getAuthenticationFilter() throws Exception {
    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
    filter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
    return filter;
  }


}
