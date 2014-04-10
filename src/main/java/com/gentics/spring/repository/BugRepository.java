package com.gentics.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gentics.spring.model.Bug;

public interface BugRepository extends JpaRepository<Bug, Long>{

	public List<Bug> findByName(String name);
}
