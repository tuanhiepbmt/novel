package com.novel.novel.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novel.novel.entity.Person;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
	private final PasswordEncoder passwordEncoder;

	public Map<String, String> register(Person person) {
		return Map.of(
				"password", passwordEncoder.encode(person.getPassword()),
				"username", person.getUsername());
		
	}
}
