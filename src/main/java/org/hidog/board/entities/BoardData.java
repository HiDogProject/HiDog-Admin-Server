package org.hidog.board.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hidog.file.entities.FileInfo;
import org.hidog.global.entities.BaseEntity;
import org.hidog.member.entities.Member;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardData extends BaseEntity { // extends BaseEntity : 날짜와 시간
    private Long seq; // 게시글 번호

    private Board board;

    private Member member;

    private String gid; // 게시글하나에 여러개의 파일을 묶는 groupid

    private String category; // 게시글 분류

    private String poster; // 작성자

    private String guestPw; // 비회원 비밀번호(수정, 삭제)

    private boolean notice;  // 공지글 여부 - true : 공지글 // 공지는 항상 앞에 나와야 하니 오더바이 desc // 1, 0 형태

    private String subject; // 게시글 제목

    private String content; // 게시글 내용

    private int viewCount; // 조회수 // 우리 사이트는 조회수가 그렇게 많진 않을 테니 자료형 int로,,,

    private int commentCount; // 댓글 수

    private boolean editorView; // true : 에디터를 통해서 작성한 경우

    private Long listOrder; // 1차 정렬 순서 - 내림차순

    private String listOrder2 = "R"; // 답글 2차 정렬 -> 오름차순

    //private int depth; // 답글 들여쓰기 정도

    private String ip; // IP 주소
    // 커뮤니티 사이트의 경우 이상한 회원 차단시키는 용
    // updatable=false : 내가 차단시킨 회원의 ip가 내 ip로 수정되면 안됨, 그럼 내가 차단됨

    private String ua; // User-Agent : 브라우저 정보
    // 통계 : 요즘 사용자는 어떤 장비를 통해서 브라우저 접속하나, 사용자는 보통 모바일, pc 중에 어떤걸 통해 많이 접속하나

    private Long num1; // 추가 필드1 : 정수
    private Long num2; // 추가 필드2 : 정수
    private Long num3; // 추가 필드3 : 정수
    // 추가필드가 필요한 이유 : 게시글을 쓰는데 상품후기, 상품문의를 만들고 싶음
    // -> 상품번호가 필요함,
    // -> 근데 보드데이터엔티티에 상품번호 칼럼을 넣기엔 너무 짜침 + 다양하게 활용 힘듬
    // -> 추가필드를 활용하자
    // num1 = 상품번호로 활용

    private String text1; // 추가 필드1 : 한줄 텍스트

    private String text2; // 추가 필드2 : 한줄 텍스트

    private String text3; // 추가 필드3 : 한줄 텍스트

    // ex) 상품 후기?

    private String longText1; // 추가 필드1 : 여러줄 텍스트

    private String longText2; // 추가 필드2 : 여러줄 텍스트

    private String longText3; // 추가 필드3 : 여러줄 텍스트

    private List<FileInfo> editorImages; // 에디터 첨부 이미지 파일 목록

    private List<FileInfo> attachFiles; // 첨부 파일 목록

    private boolean editable; // 수정, 삭제 가능 여부

    private boolean commentable; // 댓글 작성 가능 여부

    private boolean showEdit; //글쓰기, 수정 버튼 노출 여부

    private boolean showDelete; //글 삭제 버튼 노출 여부

    private boolean showList; //글 목록 버튼 노출 여부

    private boolean mine; //게시글 소유자

    private String formattedCreatedAt; // 게시글 조회용 날짜
}