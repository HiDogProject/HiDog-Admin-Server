package org.hidog.board.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hidog.board.entities.Board;
import org.hidog.board.repositories.BoardRepository;
import org.hidog.file.services.FileDeleteService;
import org.hidog.global.Utils;
import org.hidog.global.exceptions.script.AlertException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardConfigDeleteService {

    private final BoardRepository boardRepository;
    private final BoardConfigInfoService configInfoService;
    private final FileDeleteService fileDeleteService;
    private final Utils utils;

    /**
     * 게시판 삭제
     * 
     * @param bid : 게시판 아이디
     */
    public void delete(String bid) {
        Board board = configInfoService.get(bid);

        String gid = board.getGid();

        boardRepository.delete(board);

        boardRepository.flush();

        fileDeleteService.delete(gid);
    }

    public void deleteList(List<Integer> chks) {
        if (chks == null || chks.isEmpty()) {
            throw new AlertException("삭제할 게시판을 선택하세요.", HttpStatus.BAD_REQUEST);
        }

        for (int chk : chks) {
            String bid = utils.getParam("bid_" + chk);
            delete(bid);
        }
    }
}
