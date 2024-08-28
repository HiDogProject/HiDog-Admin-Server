package org.hidog.member.controllers;

import lombok.Data;
import org.hidog.global.CommonSearch;

@Data
public class MemberSearch extends CommonSearch {
    /**
     * sopt 검색옵션
     * ALL - (통합검색) - email, userName
     * email - 이메일로 검색
     * userName - 닉네임으로 검색
     */

    private String email;
    private String userName;

    private String authority;
}
