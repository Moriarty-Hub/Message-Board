package com.service;

import com.bean.Message;
import com.mapper.MessageMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private BoardService boardService;

    @Test
    public void testAddMessage() {
        String username = "root";
        String messageContent = "Hello, world!";
        //使用ArgumentCaptor来捕捉方法的参数
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        boardService.addMessage(username, messageContent);
        //注意save的参数的写法
        // verify(messageMapper, times(1)).save(captor.capture());
        // verify(messageMapper, times(1)).save(any());//这行代码可以有也可以没有
        //验证save方法保存时，使用的是不是给传入的值。举例：如果有人改动源码，把creator和content值改成了其他值，这个测试就会报错
        Assertions.assertEquals(messageContent,captor.getValue().getContent());
        Assertions.assertEquals(username,captor.getValue().getCreator());
    }

    @Test
    public void testGetAllMessageOrderByCreateTimeDesc() {
        // LinkedList<Message> message = (LinkedList<Message>) Collections.singletonList(new Message());
        // when(messageMapper.selectAllMessageOrderByCreatedTimeDesc()).thenReturn(message);

        boardService.getAllMessageOrderByCreateTimeDesc();
        verify(messageMapper, times(1)).selectAllMessageOrderByCreatedTimeDesc();
    }

}
