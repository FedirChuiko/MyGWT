package com.myApp.gwt.client;

import com.google.gwt.event.shared.EventBus;
import com.myApp.gwt.client.presenter.HomePagePresenter;
import com.myApp.gwt.client.presenter.LoginPagePresenter;

import java.util.logging.Logger;

/**
 * Created by Fedir on 27.02.2016.
 */
public interface IClientFactory {
    public Logger getLogger();

    public EventBus getEventBus();

    public LoginServiceAsync getUserServiceAsync();

    public LoginPagePresenter getLoginPagePresenter();

    public HomePagePresenter getHomePagePresenter();
}
