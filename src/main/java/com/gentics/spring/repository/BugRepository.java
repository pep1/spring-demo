package com.gentics.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.gentics.spring.model.Bug;
import com.gentics.spring.model.Tag;

public interface BugRepository extends JpaRepository<Bug, Long>, QueryDslPredicateExecutor<Bug>{

	public List<Bug> findByName(String name);

	public List<Bug> findByTagsIn(List<Tag> tags);
}
