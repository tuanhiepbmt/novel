package com.novel.novel.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novel.novel.entity.Person;
import com.novel.novel.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class PersonApi {
	private final PersonService personService;
	
	@GetMapping("/test")
	public String test() {
		return "testing success";
	}

	@PostMapping("/register")
	public Map<String, String> register(@RequestBody Person person) {
		return personService.register(person);
	}
}
