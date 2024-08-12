package org.hidog.board.services;

import lombok.RequiredArgsConstructor;
import org.hidog.board.controllers.RequestBoardConfig;
import org.hidog.board.entities.Board;
import org.hidog.board.repositories.BoardRepository;
import org.hidog.file.services.FileUploadDoneService;
import org.hidog.global.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {

    private final BoardRepository boardRepository;
    private final FileUploadDoneService fileUploadDoneService;
    private final Utils utils;


    public void save(RequestBoardConfig form){
        String bid = form.getBid();
        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode : "add";

        Board board = boardRepository.findById(bid).orElseGet(Board::new);

        if(mode.equals("add")){ //게시판 등록시 gid, bid 등록 -> 수정시에는 변경 X

        }

        board = new ModelMapper().map(form, Board.class);
        save(board);
    }

    public void save(Board board){
        boardRepository.saveAndFlush(board);
        fileUploadDoneService.process(board.getGid());
    }
    
}
