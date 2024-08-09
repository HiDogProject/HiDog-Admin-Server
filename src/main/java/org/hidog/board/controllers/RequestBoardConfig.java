package org.hidog.board.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestBoardConfig {

    private String mode = "add";

    private String gid = UUID.randomUUID().toString();

    private int listOrder; // 진열 가중치

    @NotBlank
    private String bid; // 게시판 아이디

    @NotBlank
    private String bName; // 게시판 이름

    private boolean active; // 사용 여부

    private int rowsPerPage = 20; // 1페이지 게시글 수

    private int pageCountPc = 10; // PC 페이지 구간 갯수

    private int pageCountMobile = 5; // Mobile 페이지 구간 갯수

    private boolean useComment; // 댓글 사용 여부

    private boolean useEditor; // 에디터 사용 여부

    private boolean useUploadImage; // 이미지 첨부 사용 여부

    private boolean useUploadFile; // 파일 첨부 사용 여부

    private String category; // 게시판 분류
}
