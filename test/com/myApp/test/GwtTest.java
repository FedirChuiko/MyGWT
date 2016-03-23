package com.myApp.test;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.myApp.gwt.client.AppController;
import com.myApp.gwt.client.ClientFactory;
import com.myApp.gwt.client.ITimeProvider;
import com.myApp.gwt.client.LoginServiceAsync;
import com.myApp.gwt.client.internationalizaton.MyConstants;
import com.myApp.gwt.client.widget.HomePageView;
import com.myApp.gwt.client.widget.LoginPageView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Created by Fedir on 16.03.2016.
 */
@RunWith(GwtMockitoTestRunner.class)
public class GwtTest {
    private ClientFactory clientFactory;
    private AppController appController;

    @GwtMock
    private LoginServiceAsync loginService;

    @GwtMock
    private HomePageView homePageView;

    @GwtMock
    private LoginPageView loginPageView;


    @GwtMock
    private HasWidgets widgets;

    @GwtMock
    private Button button;

    @GwtMock
    private MyConstants constants;

    @Mock
    private ITimeProvider timeProvider;

    @Before
    public void gwtSetUp() throws Exception {
        Mockito.doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String username = (String) invocation.getArguments()[0];
                String password = (String) invocation.getArguments()[1];
                AsyncCallback callback = (AsyncCallback) invocation.getArguments()[2];
                if (username.equals("username1") && password.equals("password1")) {
                    callback.onSuccess("FirstName1");
                } else
                    callback.onSuccess(null);
                return null;
            }
        }).when(loginService)
                .loginUser(Mockito.any(String.class), Mockito.any(String.class), (AsyncCallback) Mockito.any());

        Mockito.when(constants.goodMorning()).thenReturn("morning,");
        Mockito.when(constants.goodEvening()).thenReturn("evening,");
        Mockito.when(constants.goodDay()).thenReturn("day,");
        Mockito.when(constants.goodNight()).thenReturn("night,");
        clientFactory = new ClientFactory();
        appController = new AppController(clientFactory, widgets, widgets);
        appController.go();
        clientFactory.getHomePagePresenter().timeProvider = timeProvider;
        clientFactory.getHomePagePresenter().view = homePageView;
        clientFactory.getLoginPagePresenter().view = loginPageView;


    }


    void checkGreetingMessage(int time, String greetings) {
        Mockito.when(timeProvider.getCurrentHour()).thenReturn(time);
        clientFactory.getLoginPagePresenter().onLoginButtonClicked("username1", "password1");
        Mockito.verify(homePageView).setGreetings(greetings + ",FirstName1");
        Mockito.reset(homePageView);
    }

    @Test
    public void testSucessfullLogin() {
        checkGreetingMessage(0, "night");
        checkGreetingMessage(5, "night");
        checkGreetingMessage(6, "morning");
        checkGreetingMessage(8, "morning");
        checkGreetingMessage(9, "day");
        checkGreetingMessage(18, "day");
        checkGreetingMessage(19, "evening");
        checkGreetingMessage(22, "evening");
        checkGreetingMessage(23, "night");
    }

    @Test
    public void testWrongLogin() {
        Mockito.when(timeProvider.getCurrentHour()).thenReturn(0);
        clientFactory.getLoginPagePresenter().onLoginButtonClicked("username1", "wrongPassword");
        Mockito.verify(loginPageView).showErrorMessage();
    }
}