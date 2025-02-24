package com.novel.novel.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novel.novel.configuration.JwtUtil;
import com.novel.novel.dto.LoginDto;
import com.novel.novel.dto.LoginResponeDto;
import com.novel.novel.entity.Role;
import com.novel.novel.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AuthenticationApi {
	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto auth) {
		UserDetails user = userDetailsServiceImpl.loadUserByUsername(auth.getUsername());

		if (user != null && passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
			LoginResponeDto loginReq = new LoginResponeDto();
			loginReq.setToken(jwtUtil.generateToken(user.getUsername()));
			loginReq.setUsername(user.getUsername());
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			List<Role> roles = authorities.stream().map(Role.class::cast).collect(Collectors.toList());
			loginReq.setRole(roles);
			return ResponseEntity.ok(loginReq);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is not correct");
	}
}
