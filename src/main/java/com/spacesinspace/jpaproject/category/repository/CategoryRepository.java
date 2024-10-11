package com.spacesinspace.jpaproject.category.repository;

import com.spacesinspace.jpaproject.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Integer> {

    @Query(value="SELECT c.category_code, c.category_name, c.ref_category_code " +
            "FROM tbl_category c " +
            "ORDER BY c.category_code ASC",
            nativeQuery = true)
    List<Category> findAllCategory();
}
