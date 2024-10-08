package org.hidog.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hidog.board.entities.BoardData;
import org.hidog.board.services.BoardDataService;
import org.hidog.global.ListData;
import org.hidog.global.Pagination;
import org.hidog.global.Utils;
import org.hidog.global.exceptions.ExceptionProcessor;
import org.hidog.member.constants.Authority;
import org.hidog.member.entities.Member;
import org.hidog.member.services.MemberInfoService;
import org.hidog.member.services.MemberSaveService;
import org.hidog.menus.Menu;
import org.hidog.menus.MenuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements ExceptionProcessor {

    @Autowired
    private BoardDataService boardDataService;

    private final MemberInfoService memberInfoService;
    private final MemberSaveService memberSaveService;

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

    @GetMapping("/{memberId}/posts")
    public String getMemberPosts(@PathVariable("memberId") Long memberId, Model model) {
        commonProcess("posts", model);

        Member member = memberInfoService.findById(memberId);
        List<BoardData> posts = boardDataService.findPostsByMemberId(memberId);

        model.addAttribute("member", member);
        model.addAttribute("posts", posts);

        return "member/member-posts";
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
    public String updateAuthority(
            @RequestParam("memberId") Long memberId,
            @RequestParam("authority") Authority newAuthority) {

        memberSaveService.updateAuthority(memberId, newAuthority);

        return "redirect:/member/authority";
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
        } else if (mode.equals("posts")) {
            pageTitle = "회원 게시물 목록";
        }

        List<String> addScript = new ArrayList<>();

        if (mode.equals("posts")) {
            addScript.add("member");
        }

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("subMenuCode", mode);
        model.addAttribute("addScript", addScript);
    }
}