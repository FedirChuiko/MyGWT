package test;

import com.myApp.POJO.Password;
import com.myApp.POJO.domain.User;
import com.myApp.POJO.repository.UserRepository;
import com.myApp.gwt.server.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fedir on 04.03.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LoginServiceImplTest.AddressServiceTestContextConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class LoginServiceImplTest {

    @Configuration
    static class AddressServiceTestContextConfiguration {
        @Bean
        public LoginServiceImpl loginService() {
            return new LoginServiceImpl();
        }

        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }
    }


    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        User user1 = new User();
        user1.setFirstname("testFirstName1");
        user1.setPassword("testPassword1");
        user1.setUsername("testUserName1");
        loginService.save(user1);

        User user2 = new User();
        user2.setFirstname("testFirstName2");
        user2.setPassword("testPassword2");
        user2.setUsername("testUserName2");
        loginService.save(user2);

        ArrayList<User> list= new ArrayList<User>();

        list.add(user1);
        list.add(user2);
        Mockito.when(userRepository.findAll()).thenReturn(list);
        Mockito.when(userRepository.findOne(user1.getUsername())).thenReturn(user1);
    }
    @Test()
    public void testGetAll()  {
        List<User> list = loginService.getAll();
        Assert.assertEquals("testFirstName1", list.get(0).getFirstname());
        Assert.assertEquals("testFirstName2", list.get(1).getFirstname());
    }
    @Test()
    public void testSave()  {
        User user3 = new User();
        user3.setFirstname("testFirstName3");
        user3.setPassword("testPassword3");
        user3.setUsername("testUserName3");
        loginService.save(user3);
        verify(userRepository, times(1)).save(user3);
        Assert.assertEquals(Password.getHashedPassword("testPassword3",user3.getSalt()), user3.getPassword());
    }
    @Test()
    public void testLogin()  {
        Assert.assertEquals(loginService.loginUser("testUserName1","testPassword1"),"testFirstName1");
        Assert.assertEquals(loginService.loginUser("testUserName1","wrongPassword"),null);
    }

}
