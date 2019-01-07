package com.mooovi.business.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.mooovi.business.entity.User;

public interface UserService {
	
	User save(User user, MultipartFile file) throws IOException;
	User findOne(Long id);
	byte[] downloadProfileImage(Long userId) throws IOException;

}
