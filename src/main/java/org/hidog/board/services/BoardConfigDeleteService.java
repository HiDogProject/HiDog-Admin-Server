package org.hidog.board.services;

import lombok.RequiredArgsConstructor;
import org.hidog.board.entities.Board;
import org.hidog.board.repositories.BoardRepository;
import org.hidog.file.services.FileDeleteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardConfigDeleteService {

    private final BoardConfigInfoService infoService;
    private final FileDeleteService fileDeleteService;
    private final BoardRepository boardRepository;

    public void delete(String bid) {
        Board board = infoService.get(bid);
        String gid = board.getGid();

        boardRepository.delete(board); //게시판삭제
        boardRepository.flush();
        fileDeleteService.delete(gid); //게시판 관련 파일삭제

    }
}
