package com.service;

import com.entity.Message;
import com.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    public void testAddMessage() {
        String username = "root";
        String messageContent = "Hello, world!";

        boardService.addMessage(username, messageContent);
        verify(messageRepository, times(1)).save(any());
    }

    @Test
    public void testGetAllMessageOrderByCreateTimeDesc() {
        Iterable<Message> message = Collections.singletonList(new Message());
        when(messageRepository.findAllByOrderByCreateTimeDesc()).thenReturn(message);

        boardService.getAllMessageOrderByCreateTimeDesc();
        verify(messageRepository, times(1)).findAllByOrderByCreateTimeDesc();
    }

}
