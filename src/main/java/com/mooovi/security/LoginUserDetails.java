package com.mooovi.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.mooovi.business.entity.User;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User{

	private static final long serialVersionUID = 1L;
	private Long userId;

    public LoginUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }

}