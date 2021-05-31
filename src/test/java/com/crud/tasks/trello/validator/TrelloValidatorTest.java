package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrelloValidatorTest {

    @Mock
    private Logger logger;

    @InjectMocks
    TrelloValidator trelloValidator;

    @Test
    void shouldFilterBoard() {
        //given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(new TrelloBoard("1", "test", trelloLists));
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(0, validatedBoards.size());
    }

    @Test
    void shouldNotFilterBoard() {
        //given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(new TrelloBoard("1", "board name", trelloLists));
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(1, validatedBoards.size());
    }

    @Test
    void validateEmptyBoard() {
        //given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        assertEquals(0, validatedBoards.size());
    }




}