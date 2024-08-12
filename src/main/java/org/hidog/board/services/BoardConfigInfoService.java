package org.hidog.board.services;

import lombok.RequiredArgsConstructor;
import org.hidog.board.controllers.RequestBoardConfig;
import org.hidog.board.entities.Board;
import org.hidog.board.exceptions.BoardNotFoundException;
import org.hidog.board.repositories.BoardRepository;
import org.hidog.file.entities.FileInfo;
import org.hidog.file.services.FileInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigInfoService {

    private final BoardRepository boardRepository;
    private final FileInfoService fileInfoService;


    public Board get(String bid){
        Board board = boardRepository.findById(bid).orElseThrow(BoardNotFoundException::new);
        addBoardInfo(board);

        return board;
    }

    public RequestBoardConfig getForm(String bid){
        Board board = get(bid);

        RequestBoardConfig form = new ModelMapper().map(board, RequestBoardConfig.class);
        form.setListAccessType(board.getListAccessType().name());
        form.setViewAccessType(board.getViewAccessType().name());
        form.setWriteAccessType(board.getWriteAccessType().name());
        form.setReplyAccessType(board.getReplyAccessType().name());
        form.setCommentAccessType(board.getCommentAccessType().name());

        form.setMode("edit");

        return form;
    }

    /**
     * 게시판 설정 추가 정보
     * 에디터 첨부 파일 목록
     * @param board
     */
    public void addBoardInfo(Board board){
        String gid = board.getGid();
        List<FileInfo> htmlTopImages = fileInfoService.getList(gid, "html_top");
        List<FileInfo> htmlBottomImages = fileInfoService.getList(gid, "html_bottom");

        board.setHtmlTopImages(htmlTopImages);
        board.setHtmlBottomImages(htmlBottomImages);

    }


}
