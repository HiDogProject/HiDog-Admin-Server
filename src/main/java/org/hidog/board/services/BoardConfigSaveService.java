package org.hidog.board.services;

import lombok.RequiredArgsConstructor;
import org.hidog.board.controllers.RequestBoardConfig;
import org.hidog.board.entities.Board;
import org.hidog.board.repositories.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {

    private final BoardRepository boardRepository;

    public void save(RequestBoardConfig form){
        Board board = new ModelMapper().map(form, Board.class);
        save(board);
    }

    public void save(Board board){
        boardRepository.save(board);
    }
    
}
