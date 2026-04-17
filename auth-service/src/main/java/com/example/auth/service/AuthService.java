package com.example.auth.service;

public interface AuthService {
	String login(String username, String password);
	String register(String username, String password);
}
