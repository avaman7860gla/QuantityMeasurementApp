package com.example.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public String login(String username, String password) {
		User user=userRepository.findByUsername(username)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid Credentials");
		}
		
		return jwtUtil.generateToken(user.getUsername());
		
	}

	@Override
	public String register(String username, String password) {
		if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);
		
		return "User registered successfully";
	}
	
}
