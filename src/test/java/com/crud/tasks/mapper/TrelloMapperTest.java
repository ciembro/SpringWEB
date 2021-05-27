package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void testMapToList(){
        //given
        TrelloListDto trelloListDto = new TrelloListDto("1", "test list", false);
        List<TrelloListDto> trelloListDtos = Arrays.asList(trelloListDto);
        //when
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDtos);
        //then
        assertEquals(trelloListDtos.size(), trelloList.size());
        assertEquals("1", trelloList.get(0).getId());
        assertEquals("test list", trelloList.get(0).getName());
        assertFalse(trelloList.get(0).isClosed());
    }

    @Test
    void testMapToListDto(){
        TrelloList trelloList = new TrelloList("1", "test list", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList);
        //when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //then
        assertEquals(trelloLists.size(), trelloListDtos.size());
        assertEquals("1", trelloListDtos.get(0).getId());
        assertEquals("test list", trelloListDtos.get(0).getName());
        assertFalse(trelloListDtos.get(0).isClosed());
    }

    @Test
    void testMapToBoards(){
        //given
        TrelloListDto trelloListDto = new TrelloListDto("1", "test list", false);
        List<TrelloListDto> trelloListDtos = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test board", "1", trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(trelloBoardDto);
        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //then
        assertEquals(trelloBoardDtos.size(), trelloBoards.size());
        assertEquals(trelloBoardDtos.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
    }

    @Test
    void testMapToBoard(){
        //given
        TrelloListDto trelloListDto = new TrelloListDto("1", "test list", false);
        List<TrelloListDto> trelloListDtos = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test board", "1", trelloListDtos);
        //when
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);
        //then
        assertEquals(trelloBoardDto.getId(), trelloBoard.getId());
        assertEquals(trelloBoardDto.getName(), trelloBoard.getName());
        assertEquals(trelloBoardDto.getLists().size(), trelloBoard.getLists().size());
    }

    @Test
    void testMapToBoardsDto(){
        //given
        TrelloList trelloList = new TrelloList("1", "test list", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "test board", trelloLists);
        List<TrelloBoard> trelloBoards = Arrays.asList(trelloBoard);
        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //then
        assertEquals(trelloBoardDtos.size(), trelloBoards.size());
        assertEquals(trelloBoards.get(0).getId(),trelloBoardDtos.get(0).getId());
        assertEquals( trelloBoards.get(0).getName(),trelloBoardDtos.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
    }

    @Test
    void testMapToCard(){
        //given
        TrelloCardDto cardDto = new TrelloCardDto("name", "desc", "1", "id");
        //when
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        //then
        assertEquals(cardDto.getName(), card.getName());
        assertEquals(cardDto.getDescription(), card.getDescription());
        assertEquals(cardDto.getPos(), card.getPos());
        assertEquals(cardDto.getListId(), card.getListId());
    }

    @Test
    void testMapToCardDto(){
        //given
        TrelloCard card = new TrelloCard("name", "desc", "1", "id");
        //when
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        //then
        assertEquals(cardDto.getName(), card.getName());
        assertEquals(cardDto.getDescription(), card.getDescription());
        assertEquals(cardDto.getPos(), card.getPos());
        assertEquals(cardDto.getListId(), card.getListId());
    }

}