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


    }

    @Test
    public void testLogin() {
        String greetings = "night";
        for (int time = 0; time <= 24; time++) {
            if (time == 6)
                greetings = "morning";
            if (time == 9)
                greetings = "day";
            if (time == 19)
                greetings = "evening";
            if (time == 23)
                greetings = "night";
            Mockito.when(timeProvider.getCurrentHour()).thenReturn(time);
            clientFactory.getLoginPagePresenter().onLoginButtonClicked("username1", "password1");
            Mockito.verify(homePageView).setGreetings(greetings + ",FirstName1");
            Mockito.reset(homePageView);
        }
    }
}