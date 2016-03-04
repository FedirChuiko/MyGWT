package com.myApp.gwt.client;

import com.google.gwt.event.shared.EventBus;
import com.myApp.gwt.client.widget.HomePageView;
import com.myApp.gwt.client.widget.LoginPageView;

import java.util.logging.Logger;

/**
 * Created by Fedir on 27.02.2016.
 */
public interface IClientFactory {
    public Logger getLogger();
    public EventBus getEventBus();
    public LoginPageView getLoginPageView();
    public HomePageView getHomePageView();
    public LoginServiceAsync getUserServiceAsync() ;
}
