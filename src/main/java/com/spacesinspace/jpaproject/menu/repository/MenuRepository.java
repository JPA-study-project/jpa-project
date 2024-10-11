package com.spacesinspace.jpaproject.menu.repository;

import com.spacesinspace.jpaproject.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice);
}
