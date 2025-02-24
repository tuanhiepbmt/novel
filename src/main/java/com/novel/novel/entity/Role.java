package com.novel.novel.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}
}
