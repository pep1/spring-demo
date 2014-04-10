package com.gentics.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gentics.spring.model.Bug;

public interface BugRepository extends JpaRepository<Bug, Long>{

}
