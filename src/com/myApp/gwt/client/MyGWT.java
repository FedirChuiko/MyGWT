package com.myApp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.myApp.gwt.client.event.LoginEvent;
import com.myApp.gwt.client.event.LoginEventHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MyGWT implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final ClientFactory clientFactory = GWT.create(ClientFactory.class);

        clientFactory.getLogger().log(Level.SEVERE, "Client started");
        clientFactory.getEventBus().addHandler(LoginEvent.TYPE,new LoginEventHandler() {
            @Override
            public void login(LoginEvent event) {
                RootPanel.get("loginform").clear(true);
                clientFactory.getHomePageView().init(event.firstname);
                RootPanel.get("homepage").add(clientFactory.getHomePageView());
            }
        });
        RootPanel.get("loginform").add(clientFactory.getLoginPageView());

    }


}
