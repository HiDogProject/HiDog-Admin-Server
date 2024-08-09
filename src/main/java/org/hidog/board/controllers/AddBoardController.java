package org.hidog.board.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hidog.board.services.BoardConfigSaveService;
import org.hidog.board.validators.BoardConfigValidator;
import org.hidog.global.exceptions.ExceptionProcessor;
import org.hidog.menus.Menu;
import org.hidog.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class AddBoardController implements ExceptionProcessor {

    private final BoardConfigSaveService saveService;
    private final BoardConfigValidator validator;

    @ModelAttribute
    public RequestBoardConfig requestBoardConfig() {
        return new RequestBoardConfig();
    }


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
    public String list(@ModelAttribute RequestBoardConfig form) {
        return "board/add";
    }

    //게시판 등록
    @PostMapping("/add")
    public String add(@Valid RequestBoardConfig form, Errors errors){
        validator.validate(form, errors);
        if (errors.hasErrors()) {
            return "board/add";
        }
        saveService.save(form);
        return "redirect:/board";
    }
}
