package org.hidog.board.controllers;

import lombok.RequiredArgsConstructor;
import org.hidog.menus.Menu;
import org.hidog.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ListBoardController {


    @ModelAttribute("menuCode")
    public String getMenuCode() {
        return "board";
    }

    @ModelAttribute("subMenuCode")
    public String getSubMenuCode() {
        return "list";
    }

    @ModelAttribute("subMenus")
    public List<MenuDetail> getSubMenus() {
        return Menu.getMenus("board");
    }

    @ModelAttribute("pageTitle")
    public String getPageTitle() {
        return "게시판 관리";
    }


    //게시판 목록 페이지
    @GetMapping
    public String list() {



        return "board/list";
    }


}

