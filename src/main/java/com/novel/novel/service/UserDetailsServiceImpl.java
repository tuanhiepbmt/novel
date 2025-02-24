package com.novel.novel.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.novel.novel.entity.Person;
import com.novel.novel.entity.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Person person = Person.builder()
				.id(1L)
				.name("hiep")
				.username("tuanhiep")
				.password("$2a$10$8Lm9FJmHrBw9S8TkTDXciOcBV7XZ1R7I6PVaahwAKDcqmNVqVN.Dy")
				.role(List.of(
						Role.builder().id(1L).name("ADMIN").build(),
						Role.builder().id(2L).name("CLIENT").build()))
				.build();
		if(person == null || !person.getUsername().equals(username)) {
			return null;
		}

		return new User(person.getUsername(), person.getPassword(), person.getRole());
	}

}
