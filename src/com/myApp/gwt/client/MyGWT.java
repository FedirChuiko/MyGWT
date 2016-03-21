package com.myApp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MyGWT implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final IClientFactory clientFactory = GWT.create(ClientFactory.class);
        final AppController controller = new AppController(clientFactory, RootPanel.get("loginform"), RootPanel.get("homepage"));
        controller.go();

    }


}
