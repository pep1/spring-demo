package com.gentics.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gentics.spring.model.Bug;
import com.gentics.spring.repository.BugRepository;

@Controller
@RequestMapping("/bug")
public class BugController {

	@Autowired
	private BugRepository repo;

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody
	List<Bug> list() {
		return repo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Bug create (@RequestBody Bug bug) {
		return repo.save(bug);
	}
	
	@RequestMapping(value="/findbyname", method=RequestMethod.GET)
	public @ResponseBody
	List<Bug> findByName(@RequestParam(value = "name", required = true) String name) {
		return repo.findByName(name);
	}

}
