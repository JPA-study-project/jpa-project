package com.spacesinspace.jpaproject.category.repository;

import com.spacesinspace.jpaproject.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Integer> {
	

	
}
