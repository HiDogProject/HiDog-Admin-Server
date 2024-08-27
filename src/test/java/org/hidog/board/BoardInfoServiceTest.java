package org.hidog.board;

import org.hidog.board.controllers.BoardDataSearch;
import org.hidog.board.entities.BoardData;
import org.hidog.board.services.BoardInfoService;
import org.hidog.global.ListData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardInfoServiceTest {

    @Autowired
    private BoardInfoService infoService;

    @Test
    void test1(){

        BoardDataSearch search = new BoardDataSearch();
        ListData<BoardData> data = infoService.getList(search);
        System.out.println(data);


    }
}
