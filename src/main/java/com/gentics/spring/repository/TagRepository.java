package com.gentics.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gentics.spring.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

	public Tag findByName(String name);
}
