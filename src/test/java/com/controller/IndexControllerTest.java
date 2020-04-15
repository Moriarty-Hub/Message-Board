package com.controller;

import com.service.RegisterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private Model model;
    private HttpSession httpSession;

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private IndexController indexController;

    @Before
    public void setUp() {
        model = Mockito.mock(Model.class);
        httpSession = Mockito.mock(HttpSession.class);
    }

    @Ignore
    @Test
    public void testShowIndexPage() {
        Assert.assertEquals("index", indexController.showIndexPage());
    }

    @Ignore
    @Test
    public void test1LogIn() {  // When username and password are both valid.
        when(registerService.verifyUser(anyString(), anyString())).thenReturn(true);

        String result = indexController.logIn(anyString(), anyString(), model, httpSession);
        Assert.assertEquals("intermediate-page", result);
        verify(model, times(1)).addAttribute("info", "You have logged in");
        verify(model, times(1)).addAttribute("redirectedPage", "board");
    }

    @Ignore
    @Test
    public void test2LogIn() { // when username or password is invalid.
        when(registerService.verifyUser(anyString(), anyString())).thenReturn(false);

        String result = indexController.logIn(anyString(), anyString(), model, httpSession);
        Assert.assertEquals("intermediate-page", result);
        verify(httpSession, times(1)).setAttribute(anyString(), anyString());
        verify(model, times(1)).addAttribute("info", "Your username or password is not correct");
        verify(model, times(1)).addAttribute("redirectedPage", "index");
    }

    @Ignore
    @Test
    public void testShowRegisterPage() {
        Assert.assertEquals("register", indexController.showRegisterPage());
    }

    @Ignore
    @Test
    public void test1Register() {  // The username has already exist
        when(registerService.isUsernameExist(anyString())).thenReturn(true);

        String result = indexController.register(anyString(), "", "", model);
        Assert.assertEquals("intermediate-page", result);
        verify(model, times(1)).addAttribute("info", "This username has already exist, please choose another one");
        verify(model, times(1)).addAttribute("redirectedPage", "register");
    }

    @Ignore
    @Test
    public void test2Register() {  // The username has not exist, but the two password are not equal.
        when(registerService.isUsernameExist(anyString())).thenReturn(false);

        String result = indexController.register(anyString(), "password1", "password2", model);
        Assert.assertEquals("intermediate-page", result);
        verify(model, times(1)).addAttribute("info", "Your password is not correct, please input again");
        verify(model, times(1)).addAttribute("redirectedPage", "register");
    }

    @Ignore
    @Test
    public void test3Register() {
        when(registerService.isUsernameExist(anyString())).thenReturn(false);

        String result = indexController.register(anyString(), "password", "password", model);
        Assert.assertEquals("intermediate-page", result);
        verify(model, times(1)).addAttribute("info", "You have registered successfully, please log in");
        verify(model, times(1)).addAttribute("redirectedPage", "index");
    }
}
