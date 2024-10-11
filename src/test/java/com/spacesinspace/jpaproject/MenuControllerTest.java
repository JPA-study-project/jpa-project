package com.spacesinspace.jpaproject;

import com.spacesinspace.jpaproject.menu.dto.MenuDTO;
import com.spacesinspace.jpaproject.menu.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MenuControllerTest {

    @Autowired
    private MenuService menuService;

    @Test
    void testFindByMenuCode() throws Exception {
        int menuCode = 7;

        MenuDTO foundMenu = menuService.findMenuByCode(menuCode);
        assertEquals(menuCode, foundMenu.getMenuCode());
    }
}
