package org.hidog.board;

import org.hidog.board.controllers.BoardDataSearch;
import org.hidog.board.entities.BoardData;
import org.hidog.board.services.BoardInfoService;
import org.hidog.board.services.BoardUpdateService;
import org.hidog.global.ListData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardUpdateServiceTest {

    @Autowired
    private BoardUpdateService updateService;

    @Autowired
    private BoardInfoService infoService;

    @Test
    void test1(){
        ListData<BoardData> data = infoService.getList(new BoardDataSearch());
        List<BoardData> items = data.getItems().stream().map(item -> {
            item.setSubject("(수정)" + item.getSubject());
            return item;
        }).toList();

        updateService.update("update", items);

    }
}
