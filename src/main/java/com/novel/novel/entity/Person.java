package com.novel.novel.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
	private Long id;
	private String username;
	private String password;
	private String name;
	private List<Role> role;
}
