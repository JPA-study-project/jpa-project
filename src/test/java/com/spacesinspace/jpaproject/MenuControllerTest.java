package com.spacesinspace.jpaproject;

import com.spacesinspace.jpaproject.menu.dto.MenuDTO;
import com.spacesinspace.jpaproject.menu.entity.Menu;
import com.spacesinspace.jpaproject.menu.repository.MenuRepository;
import com.spacesinspace.jpaproject.menu.service.MenuService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuControllerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void testFindByMenuCode() throws Exception {
        int menuCode = 7;
        MenuDTO foundMenu = menuService.findMenuByCode(menuCode);
        System.out.println("foundMenu = " + foundMenu);
        assertEquals(menuCode, foundMenu.getMenuCode());
    }

    @Test
    void testFindAllMenu() throws Exception {
        List<Menu> foundMenuList = menuRepository.findAll();
        foundMenuList.forEach(System.out::println);
        assertNotNull(foundMenuList);
    }

    @Test
    void testFindMenuByPrice() throws Exception {
        int price = 10000;
        List<MenuDTO> menuList = menuService.findByMenuPrice(price);

        boolean isTrueOrFalse = false;
        for (MenuDTO m : menuList) {
            isTrueOrFalse = m.getMenuPrice() >= price;
            System.out.println("isTrueOrFalse = " + isTrueOrFalse);
        }

        assertTrue(isTrueOrFalse);
    }

    @Test
    void testRegistNewMenu() throws Exception {
        MenuDTO newMenu = new MenuDTO();
        newMenu.setMenuName("토마토육개장");
        newMenu.setMenuPrice(13000);
        newMenu.setCategoryCode(1);
        newMenu.setOrderableStatus("Y");

        menuService.registNewMenu(newMenu);

        Menu foundMenu = menuRepository.findByMenuName(newMenu.getMenuName());
        assertNotNull(foundMenu);
    }

    @Test
    void testModifyNewMenu() throws Exception {
        MenuDTO modifyMenu = menuService.findMenuByCode(1);
        modifyMenu.setMenuName("바뀐 메뉴명");

        menuService.modifyMenu(modifyMenu);

        Menu modifiedMenu = menuRepository.findByMenuName("바뀐 메뉴명");
        assertEquals(modifyMenu.getMenuName(), modifiedMenu.getMenuName());
    }

    @Test
    void testDeleteMenu() throws Exception {
        int menuCode = 1;
        menuService.deleteMenu(menuCode);
        assertNull(menuService.findMenuByCode(menuCode));

    }
}
