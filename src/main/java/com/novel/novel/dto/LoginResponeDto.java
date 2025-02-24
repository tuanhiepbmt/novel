package com.novel.novel.dto;

import java.util.List;

import com.novel.novel.entity.Role;

import lombok.Data;

@Data
public class LoginResponeDto {
	private String token;
	private String username;
	private List<Role> role;
}