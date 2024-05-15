package com.example.BackendMovie.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsersServiceImpl implements UserDetails {
    String ROLE_PREFIX="ROLE";
    private Long id;

    private String name;

    private String email;

    private String password;

    private String confirmPassword;
    String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority>list=new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX+role));
          return list;

        }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
