package com.myApp.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Fedir on 01.03.2016.
 */
public interface LoginServiceAsync {
    void loginUser(String username, String password,AsyncCallback<String> callback);
}
