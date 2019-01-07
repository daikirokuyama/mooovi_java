package com.mooovi.business.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mooovi.business.entity.User;
import com.mooovi.business.repository.UserRepository;
import com.mooovi.business.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User save(User user, MultipartFile file) throws IOException {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user = userRepository.save(user);
	    if (!file.isEmpty()) { // ①
	        user.setProfileImage(uploadProfileImage(file, user.getId()));  // ②
	    }
	    return user;
	}
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	
	private String uploadProfileImage(MultipartFile file, Long userId) throws IOException {
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = formatter.format(Calendar.getInstance().getTime()) + "_profile-image.jpg";
		Path path = Paths.get("upload", "users", userId.toString(), fileName);
		Files.createDirectories(path.getParent());
		Files.copy(file.getInputStream(), path);
		return fileName;
	}
	
	@Override
	public byte[] downloadProfileImage(Long userId) throws IOException {
		User user = userRepository.findOne(userId);
		Path path = Paths.get("upload", "users", user.getId().toString(), user.getProfileImage());
		return Files.readAllBytes(path);
	}

}
