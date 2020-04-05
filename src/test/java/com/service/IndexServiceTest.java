package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IndexService indexService;

    @Test
    public void test1VerifyUser() {  // Username does not exist
        when(userRepository.findUserByUsername(anyString())).thenReturn(null);

        boolean result = indexService.verifyUser(anyString(), "");
        Assert.assertFalse(result);
    }

    @Test
    public void test2VerifyUser() {  // Username and password are both correct
        User user = new User();
        user.setUsername("root");
        user.setPassword("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"); // SHA-256 hash code of "password"
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);

        boolean result = indexService.verifyUser(anyString(), "password");
        Assert.assertTrue(result);
    }

    @Test
    public void test3VerifyUser() {  // Username exists, but password is not correct
        User user = new User();
        user.setUsername("root");
        user.setPassword("4813494D137E1631BBA301D5ACAB6E7BB7AA74CE1185D456565EF51D737677B2");
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);

        boolean result = indexService.verifyUser(anyString(), "wrongPassword");
        Assert.assertFalse(result);
    }

    @Test
    public void test1IsUsernameExist() {
        when(userRepository.findUserByUsername(anyString())).thenReturn(null);

        boolean result = indexService.isUsernameExist(anyString());
        Assert.assertFalse(result);
    }

    @Test
    public void test2UsernameExist() {
        User user = new User();
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);

        boolean result = indexService.isUsernameExist(anyString());
        Assert.assertTrue(result);
    }

    @Test
    public void testAddNewUser() {
        indexService.addNewUser(anyString(), "");
        verify(userRepository, times(1)).save(any());
    }
}
