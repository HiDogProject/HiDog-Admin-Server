package org.hidog.board.controllers;

import lombok.RequiredArgsConstructor;
import org.hidog.board.services.BoardConfigSaveService;
import org.hidog.board.validators.BoardConfigValidator;
import org.hidog.global.Utils;
import org.hidog.global.exceptions.ExceptionProcessor;
import org.hidog.menus.Menu;
import org.hidog.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController implements ExceptionProcessor {

    private final Utils utils;

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

    @ModelAttribute("subMenus")
    public List<MenuDetail> getSubMenus() {
        return Menu.getMenus("board");
    }


    //게시판 목록 페이지
    @GetMapping
    public String list() {
        return "board/list";
    }

    //게시판 수정
    @PatchMapping
    public String editList(){
        return "board/edit";
    }

    //게시판 삭제
    @DeleteMapping
    public String deleteList(){
        return "board/delete";
    }

    //게시판 등록 페이지
    @GetMapping("/add")
    public String list(@ModelAttribute RequestBoardConfig form) {
        return "board/add";
    }

    //게시판 수정 페이지
    @GetMapping("/edit/{bid}")
    public String add(@PathVariable("bid") String bid, Model model){
        return "board/edit";
    }

    //게시글 등록 or 수정 처리
    @PostMapping("/save")
    public String save(){
        return "redirect:" + utils.redirectUrl("/board");
    }


    //게시글 관리
    @GetMapping("/posts")
    public String posts(Model model){
        return "board/posts";
    }


    private void commonProcess(String mode, Model model){
        String pageTitle = "게시글 목록";
        mode = StringUtils.hasText(mode) ? mode : "list";

        if(mode.equals("add")){
            pageTitle = "게시판 등록";
        }else if(mode.equals("edit")){
            pageTitle = "게시판 수정";
        }else if(mode.equals("posts")){
            pageTitle = "게시판 관리";
        }

        List<String> addScript = new ArrayList<>();

        if(mode.equals("add") || mode.equals("edit")){ //게시판 등록 or 수정
            addScript.add("ckeditor5/ckeditor");

            addScript.add("board/form");
        }

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("mode", mode);
        model.addAttribute("addScript", addScript);

    }
}
