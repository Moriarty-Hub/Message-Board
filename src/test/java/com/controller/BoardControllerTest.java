package com.controller;

import com.service.BoardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardControllerTest {

    private Model model;
    private HttpSession httpSession;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    @Before
    public void setUp() {
        model = Mockito.mock(Model.class);
        httpSession = Mockito.mock(HttpSession.class);
    }

    @Test
    public void test1GetMessageList() {  // When username equals to null
         when(httpSession.getAttribute("username")).thenReturn(null);

         String result = boardController.showBoardPage(model, httpSession);
         Assertions.assertEquals("intermediate-page", result);
         verify(model, times(2)).addAttribute(anyString(), anyString());
    }

    @Test
    public void test2GetMessageList() {  // When username is not null
        when(httpSession.getAttribute("username")).thenReturn("root");

        String result = boardController.showBoardPage(model, httpSession);
        Assertions.assertEquals("board", result);
        verify(model, times(1)).addAttribute(anyString(), any());
    }

    @Test
    public void testAddMessage() {
        when(httpSession.getAttribute("username")).thenReturn("root");

        String messageContent = "Hello, world!";
        String result = boardController.addMessage(messageContent, model, httpSession);
        Assertions.assertEquals("intermediate-page", result);
        verify(model, times(2)).addAttribute(anyString(), anyString());
        verify(boardService, times(1)).addMessage(anyString(), anyString());
    }
}
