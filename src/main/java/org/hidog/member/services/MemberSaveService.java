package org.hidog.member.services;

import lombok.RequiredArgsConstructor;
import org.hidog.member.constants.Authority;
import org.hidog.member.entities.Authorities;
import org.hidog.member.entities.Member;
import org.hidog.member.repositories.AuthoritiesRepository;
import org.hidog.member.repositories.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public void updateAuthority(Long memberId, Authority newAuthority) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 기존 권한 삭제
        authoritiesRepository.deleteByMember(member);

        // 새로운 권한 저장
        Authorities newAuthorities = Authorities.builder()
                .member(member)
                .authority(newAuthority)
                .build();

        authoritiesRepository.save(newAuthorities);
    }
}