package org.hidog.board.controllers.api;


import lombok.RequiredArgsConstructor;
import org.hidog.board.entities.Board;
import org.hidog.board.services.BoardConfigInfoService;
import org.hidog.global.exceptions.RestExceptionProcessor;
import org.hidog.global.rests.JSONData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("ApiBoardController")
@RequestMapping("/board/api")
public class BoardController implements RestExceptionProcessor {
    private final BoardConfigInfoService infoService;

    @GetMapping("/config/{bid}")
    public JSONData config(@PathVariable("bid") String bid) {
        Board board = infoService.get(bid);

        return new JSONData(board);
    }
}
