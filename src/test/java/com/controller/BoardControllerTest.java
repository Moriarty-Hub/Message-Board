package com.controller;

import com.entity.Message;
import com.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Collections;

import static org.mockito.Mockito.*; // 使用Mockito进行测试
@RunWith(MockitoJUnitRunner.class) // 这个注解代表你要开始执行测试，注意pom文件里面加了一个Mockito的依赖
public class BoardControllerTest {
    /**
     * 这个注解的意思是注入当前的测试对象，不需要new出来，Mockito会替我们完成注入动作
     */
    @InjectMocks
    private BoardController boardController;

    /**
     * Mock的意思是虚拟的，模拟的，举例如下：
     * 我要测试A方法，但是A方法里面调用了其他类的B方法，那么我不需要知道B方法的具体实现，所以我就模拟一个B方法，让它
     * 给我返回我想要的值即可，保证A方法的流程可以正常走通就行
     */
    @Mock
    private MessageRepository messageRepository;

    @Before
    public void setUp () {
        //这里可以写一些测试该类共有的对象构建，对象初始化的工作，比如下面两个方法都用到了Model、HttpSession
    }

    /**
     * 1 注意方法名，应该是你们都没有接触过的写法，很长，这个方法名就告诉了阅读者，我这个方法是测试什么的，单词分开写，一般以should开头
     * 2 对于controller的测试还有MockMVC的写法，主要是模拟一个类似postman、swagger之类的模拟器，使之可以自动发出请求，这个后面你们再了解
     * 3 这里把controller当成了一个普通的类、普通的方法来测试，一般完成这样的测试就OK了，对于必须使用加载一些资源才能完成的测试才会考虑MockMVC
     */
    @Test
    public void should_get_message_list_when_user_name_is_null () {
        Model model = Mockito.mock(Model.class); //mock Model对象
        HttpSession httpSession = mock(HttpSession.class);// mock HttpSession对象
        when(httpSession.getAttribute(anyString())).thenReturn(null); //期望该方法返回，对应if分支
        String messageList = boardController.getMessageList(model, httpSession); //调用方法
        Assertions.assertThat(messageList).isEqualTo("intermediate-page"); //断言返回值是否和预期一致
        verify(model, times(2)).addAttribute(anyString(), anyString()); //验证方法是否被调用执行，以及执行的次数
    }

    @Test
    public void should_get_message_list_when_user_name_is_not_null () {
        Model model = Mockito.mock(Model.class);
        HttpSession httpSession = mock(HttpSession.class);//上面方法和这个都使用到了Model和HttpSession对象可以考虑提取为全局对象
        Iterable<Message> messageIterable = Collections.singletonList(new Message());// 构造要返回的对象
        when(httpSession.getAttribute(anyString())).thenReturn("DXR"); //期望方法返回，非null值，对应if分支
        when(messageRepository.findAllOrOrderByCreateTimeDesc()).thenReturn(messageIterable); //期望其他类的B方法给我返回我希望的值
        String messageList = boardController.getMessageList(model, httpSession); //调用方法
        Assertions.assertThat(messageList).isEqualTo("board"); //断言返回值是否正确
        verify(model, times(1)).addAttribute(anyString(), any()); // 同上，注意第二个是any（），不是anyString（）
    }

}