package com.myApp.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.myApp.gwt.client.widget.HomePageView;
import com.myApp.gwt.client.widget.LoginPageView;

import java.util.logging.Logger;


public class ClientFactory  implements IClientFactory{
	private final EventBus eventBus = new SimpleEventBus();
	private final LoginPageView loginPageView = new LoginPageView(this);
	private final HomePageView homePageView = new HomePageView(this);
    private final LoginServiceAsync userServiceAsync = (LoginServiceAsync) GWT.create(LoginService.class);
    private final Logger logger = Logger.getLogger("helloLogger");

    public Logger getLogger() {return logger;}
	public EventBus getEventBus() { return eventBus; }
    public LoginPageView getLoginPageView() { return loginPageView; }
	public HomePageView getHomePageView() { return homePageView; }
    public LoginServiceAsync getUserServiceAsync() {return userServiceAsync; }

}
