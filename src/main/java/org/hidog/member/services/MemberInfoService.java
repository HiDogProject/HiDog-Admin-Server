package org.hidog.member.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hidog.board.repositories.BoardDataRepository;
import org.hidog.global.ListData;
import org.hidog.global.Pagination;
import org.hidog.member.MemberInfo;
import org.hidog.member.constants.Authority;
import org.hidog.member.controllers.MemberSearch;
import org.hidog.member.entities.Authorities;
import org.hidog.member.entities.Member;
import org.hidog.member.entities.QMember;
import org.hidog.member.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private MemberRepository memberRepository;

    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<Authorities> tmp = Objects.requireNonNullElse(member.getAuthorities(),//getAuthorities 가 Null 이면 뒤에 반환
                List.of(Authorities.builder()
                        .member(member)
                        .authority(Authority.USER)
                        .build()));

        List<SimpleGrantedAuthority> authorities = tmp.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().name()))
                .toList();
        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .authorities(authorities)
                .member(member)
                .build();
    }

    public ListData<Member> getList(MemberSearch search, boolean isAll) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        QMember member = QMember.member;
        BooleanBuilder andBuilder = new BooleanBuilder();

        /* 검색처리 S */
        String sopt = search.getSopt();
        String skey = search.getSkey();
        String email = search.getEmail();
        String userName = search.getUserName();

        sopt = StringUtils.hasText(sopt) ? sopt : "ALL"; //통합검색이 기본

        //키워드가 존재 할 때, 조건별 검색
        if (StringUtils.hasText(skey)) {
            /**
             * sopt 검색옵션
             * ALL - (통합검색) - email, userName
             * email - 이메일로 검색
             * userName - 닉네임으로 검색
             */
            sopt = sopt.trim();
            skey = skey.trim();
            BooleanExpression condition = null;

            if (sopt.equals("ALL")) { //통합검색
                condition = member.email.contains(skey).or(member.userName.contains(skey));

            } else if (sopt.equals("email")) { // 이메일로 검색
                condition = member.email.contains(skey);

            } else if (sopt.equals("userName")) { // 닉네임으로 검색
                condition = member.userName.contains(skey);

            }

            if (condition != null) andBuilder.and(condition);
        }

        if (StringUtils.hasText(email)) { // 이메일
            andBuilder.and(member.email.contains(email.trim()));

            if (userName != null && StringUtils.hasText(userName.trim())) { // 닉네임
                andBuilder.and(member.userName.eq(userName.trim()));
            }
        }
        //페이징 및 정렬 처리
        Pageable pageable = PageRequest.of(page-1, limit, Sort.by(desc("seq")));
        //데이터 조회
        Page<Member> data = memberRepository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        List<Member> items = data.getContent();

        return new ListData<>(items, pagination);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));
    }
}