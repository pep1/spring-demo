package com.gentics.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gentics.spring.model.Bug;
import com.gentics.spring.model.QBug;
import com.gentics.spring.model.Tag;
import com.gentics.spring.repository.BugRepository;
import com.gentics.spring.repository.TagRepository;
import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

@Controller
@RequestMapping(value = "/bug", produces = "application/json")
public class BugController {

	private static final Logger log = Logger.getLogger(BugController.class);

	@Autowired
	private BugRepository bugRepo;

	@Autowired
	private TagRepository tagRepo;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Bug> list() {
		return bugRepo.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Bug get(@PathVariable("id") Long id) {
		return bugRepo.findOne(id);
	}

	@RequestMapping(value = "/{id}/tag", method = RequestMethod.GET)
	public @ResponseBody
	Bug tag(@PathVariable("id") Long id, @RequestParam(value = "name", required = true) String name) {

		// get the bug by id
		Bug bug = get(id);
		Assert.notNull(bug, "Bug could not be found!");

		Tag tag = tagRepo.findByName(name);

		// if tag does not exist, create new one
		if (tag == null) {
			tag = new Tag();
			tag.setName(name);

			tagRepo.save(tag);
		}

		// add the tag to the bug tags
		bug.getTags().add(tag);

		return bugRepo.save(bug);
	}

	@RequestMapping(value = "/findbyname", method = RequestMethod.GET)
	public @ResponseBody
	List<Bug> findByName(@RequestParam(value = "name", required = true) String name) {
		return bugRepo.findByName(name);
	}

	@RequestMapping(value = "/findbytags/{tags}", method = RequestMethod.GET)
	public @ResponseBody
	List<Bug> findByTags(@PathVariable(value = "tags") String tagString,
			@RequestParam(value = "disjunct", required = false, defaultValue = "false") Boolean disjunct) {

		String[] tagNames = tagString.split(",");
		List<Tag> foundTags = new ArrayList<Tag>();

		for (String string : tagNames) {

			Tag tag = tagRepo.findByName(string);
			if (tag != null) {
				foundTags.add(tag);
			} else {
				String message = "specified tag " + string + " does not exist!";
				
				log.error(message);
				throw new IllegalArgumentException(message);
			}
		}

		if (disjunct) {

			// build query dsl predicate
			QBug bug = QBug.bug;
			BooleanExpression expression = bug.isNotNull();

			for (Tag tag : foundTags) {
				expression = expression.and(bug.tags.contains(tag));
			}

			return Lists.newArrayList(bugRepo.findAll(expression));
		} else {

			return bugRepo.findByTagsIn(foundTags);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Bug create(@RequestBody Bug bug) {
		return bugRepo.save(bug);
	}

}
