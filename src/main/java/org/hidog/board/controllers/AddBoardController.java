package org.hidog.board.controllers;

import lombok.RequiredArgsConstructor;
import org.hidog.board.services.BoardConfigSaveService;
import org.hidog.menus.Menu;
import org.hidog.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class AddBoardController {

    private final BoardConfigSaveService saveService;


    @ModelAttribute("menuCode")
    public String getMenuCode() {
        return "board";
    }

    @ModelAttribute("subMenuCode")
    public String getSubMenuCode() {
        return "add";
    }

    @ModelAttribute("subMenus")
    public List<MenuDetail> getSubMenus() {
        return Menu.getMenus("board");
    }

    @ModelAttribute("pageTitle")
    public String getPageTitle() {
        return "게시판 관리";
    }


    //게시판 등록 페이지
    @GetMapping("/add")
    public String list(@ModelAttribute RequestBoardConfig form, Model model) {
        return "board/add";
    }

    //게시판 등록
    @PostMapping("/add")
    public String add(RequestBoardConfig form, Model model){
        saveService.save(form);
        return "redirect:/board";
    }
}
