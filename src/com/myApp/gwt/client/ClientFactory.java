package com.myApp.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.myApp.gwt.client.presenter.HomePagePresenter;
import com.myApp.gwt.client.presenter.LoginPagePresenter;
import com.myApp.gwt.client.widget.HomePageView;
import com.myApp.gwt.client.widget.LoginPageView;

import java.util.logging.Logger;


public class ClientFactory implements IClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final LoginServiceAsync userServiceAsync = (LoginServiceAsync) GWT.create(LoginService.class);
    private final Logger logger = Logger.getLogger("helloLogger");
    private final LoginPagePresenter loginPagePresenter = new LoginPagePresenter(this, new LoginPageView());
    private final HomePagePresenter homePagePresenter = new HomePagePresenter(this, new HomePageView(), new DateBasedTimeProvider());

    public Logger getLogger() {
        return logger;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public LoginServiceAsync getUserServiceAsync() {
        return userServiceAsync;
    }

    public LoginPagePresenter getLoginPagePresenter() {
        return loginPagePresenter;
    }

    public HomePagePresenter getHomePagePresenter() {
        return homePagePresenter;
    }

}
