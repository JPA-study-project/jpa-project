package com.spacesinspace.jpaproject.menu.service;

import com.spacesinspace.jpaproject.menu.dto.MenuDTO;
import com.spacesinspace.jpaproject.menu.entity.Menu;
import com.spacesinspace.jpaproject.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

	private final MenuRepository menuRepository;
	private final ModelMapper modelMapper;

	public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}
	
	public MenuDTO findMenuByCode(int menuCode) {

		Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalAccessError::new);

		return modelMapper.map(menu, MenuDTO.class);
	}
	
	public List<MenuDTO> findMenuList() {
		
		return null;
	}

	public Page<MenuDTO> findMenuList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
				pageable.getPageSize(),
				Sort.by("menuCode").descending());

		Page<Menu> menuList = menuRepository.findAll(pageable);

		return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
	}

	public List<MenuDTO> findByMenuPrice(Integer menuPrice) {

		List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);

		return menuList
				.stream()
				.map(menu -> modelMapper.map(menu, MenuDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public void registNewMenu(MenuDTO newMenu) {

	}

	@Transactional
	public void modifyMenu(MenuDTO modifyMenu) {

	}

	/* 목차. 8. delete */
	@Transactional
	public void deleteMenu(Integer menuCode) {
		menuRepository.deleteById(menuCode);
	}
	
	
}
