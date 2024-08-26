package org.hidog.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hidog.board.controllers.RequestBoardConfig;
import org.hidog.board.controllers.RequestMember;
import org.hidog.global.ListData;
import org.hidog.global.Pagination;
import org.hidog.global.Utils;
import org.hidog.global.exceptions.ExceptionProcessor;
import org.hidog.member.constants.Authority;
import org.hidog.member.entities.Member;
import org.hidog.member.services.MemberDeleteService;
import org.hidog.member.services.MemberInfoService;
import org.hidog.member.services.MemberSaveService;
import org.hidog.menus.Menu;
import org.hidog.menus.MenuDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements ExceptionProcessor {

    private final MemberInfoService memberInfoService;
    private final MemberSaveService memberSaveService;
    private final MemberDeleteService memberDeleteService;

    private final Utils utils;

    @ModelAttribute("menuCode")
    public String getMenuCode() {
        return "member";
    }

    @ModelAttribute("subMenus")
    public List<MenuDetail> getSubMenus() {
        return Menu.getMenus("member");
    }

    /**
     * 회원 목록 페이지
     * @param model
     * @return
     */
    @GetMapping
    public String list(@ModelAttribute MemberSearch search, Model model) {
        commonProcess("list", model);

        ListData<Member> data = memberInfoService.getList(search, true);

        List<Member> items = data.getItems();
        Pagination pagination = data.getPagination();

        model.addAttribute("items", items);
        model.addAttribute("pagination", pagination);

        return "member/list";
    }

    @GetMapping("/authority")
    public String authority(@ModelAttribute MemberSearch search, Model model) {
        commonProcess("authority", model);

        ListData<Member> data = memberInfoService.getList(search, true);

        List<Member> items = data.getItems();
        Pagination pagination = data.getPagination();

        model.addAttribute("items", items);
        model.addAttribute("pagination", pagination);

        return "member/authority";
    }

    @PostMapping("/update-authority")
    public String save(@Valid RequestMember member, Errors errors, Model model) {
        String mode = config.getMode();

        commonProcess(mode, model);

        configValidator.validate(config, errors);

        if (errors.hasErrors()) {
            return "member/" + mode;
        }

        memberSaveService.save(config);


        return "redirect:" + utils.redirectUrl("/member");
    }


    /**
     * 공통 처리
     *
     * @param mode
     * @param model
     */
    private void commonProcess(String mode, Model model) {
        String pageTitle = "회원 목록";
        mode = StringUtils.hasText(mode) ? mode : "list";

        if (mode.equals("authority")) {
            pageTitle = "회원 권한 수정";
        } else if (mode.equals("list")) {
            pageTitle = "회원 목록";
        }

        List<String> addScript = new ArrayList<>();

        if (mode.equals("authority")) { // 회원 권한 수정
            addScript.add("member/authority");
        }

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("subMenuCode", mode);
        model.addAttribute("addScript", addScript);
    }
}