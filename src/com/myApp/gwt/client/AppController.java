package com.myApp.gwt.client;

import com.google.gwt.user.client.ui.HasWidgets;
import com.myApp.gwt.client.event.LoginEvent;
import com.myApp.gwt.client.event.LoginEventHandler;

import java.util.logging.Level;

/**
 * Created by Fedir on 18.03.2016.
 */
public class AppController {
    private HasWidgets loginContainer;
    private HasWidgets mainContainer;
    IClientFactory clientFactory;

    public AppController(IClientFactory clientFactory, HasWidgets loginContainer, HasWidgets mainContainer) {
        this.clientFactory = clientFactory;
        this.loginContainer = loginContainer;
        this.mainContainer = mainContainer;
    }

    public void go() {
        bind();
        clientFactory.getLoginPagePresenter().go(loginContainer);
        clientFactory.getLogger().log(Level.SEVERE, "Client started");

    }

    private void bind() {
        clientFactory.getEventBus().addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            @Override
            public void login(LoginEvent event) {
                loginContainer.clear();
                clientFactory.getHomePagePresenter().setFirstName(event.firstname);
                clientFactory.getHomePagePresenter().go(mainContainer);
            }
        });
    }
}
