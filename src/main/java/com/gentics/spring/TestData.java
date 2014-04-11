package com.gentics.spring;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gentics.spring.model.Bug;
import com.gentics.spring.model.Tag;
import com.gentics.spring.repository.BugRepository;
import com.gentics.spring.repository.TagRepository;

@Service
@Scope("singleton")
public class TestData {

	private static final Logger log = Logger.getLogger(TestData.class);

	@Autowired
	private BugRepository bugRepo;

	@Autowired
	private TagRepository tagRepo;

	@PostConstruct
	public void initialize() {
		log.debug("initializing new " + this.getClass().getSimpleName() + " instance ..");

		Tag tag1 = new Tag();
		tag1.setName("test");
		
		Tag tag2 = new Tag();
		tag1.setName("test2");
		
		tagRepo.save(Arrays.asList(new Tag[] {tag1, tag2}));

		Bug bug1 = new Bug();
		bug1.setName("Alles ist kaput!");
		bug1.setDescription("Massive data loss!");

		Bug bug2 = new Bug();
		bug2.setName("Game");
		bug2.setDescription("Ein Spiel in Content.Node?");

		Bug bug3 = new Bug();
		bug3.setName("test");
		bug3.setDescription("Das ist ein Testbug");
		bug3.getTags().add(tag1);

		bugRepo.save(Arrays.asList(new Bug[] { bug1, bug2, bug3 }));

		log.debug("Inserted test Data");
	}

}
