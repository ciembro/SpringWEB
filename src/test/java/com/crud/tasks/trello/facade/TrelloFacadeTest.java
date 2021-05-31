package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloFacadeTest {

    @InjectMocks
    TrelloFacade trelloFacade;

    @Mock
    TrelloService service;

    @Mock
    TrelloValidator validator;

    @Mock
    TrelloMapper mapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(service.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(mapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(mapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(validator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        // Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void shouldFetchTrelloBoard() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(service.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(mapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(mapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(validator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto -> {

            assertThat(trelloBoardDto.getId()).isEqualTo("1");
            assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertThat(trelloListDto.getId()).isEqualTo("1");
                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }

    @Test
    void shouldCreateCard(){
        //given
        TrelloCardDto cardDto = new TrelloCardDto("name", "desc", "pos1", "1");
        TrelloCard card = new TrelloCard("name", "desc", "pos1", "1");
        CreatedTrelloCardDto createdCardDto =
                new CreatedTrelloCardDto("1", "name", "http://test.com");

        when(service.createTrelloCard(cardDto)).thenReturn(createdCardDto);
        when(mapper.mapToCard(cardDto)).thenReturn(card);
        when(mapper.mapToCardDto(card)).thenReturn(cardDto);
        //when
        CreatedTrelloCardDto createdCard = trelloFacade.createCard(cardDto);
        //then
        assertEquals("1", createdCard.getId());
        assertEquals("name", createdCard.getName());
        assertEquals("http://test.com", createdCard.getShortUrl());

    }


}