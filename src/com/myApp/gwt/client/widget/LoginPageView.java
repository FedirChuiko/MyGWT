package com.myApp.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.myApp.gwt.client.IClientFactory;
import com.myApp.gwt.client.event.LoginEvent;
import com.myApp.gwt.client.internationalizaton.MyConstants;

import java.util.logging.Level;

/**
 * Created by Fedir on 24.02.2016.
 */
public class LoginPageView extends Composite {

    interface MyUiBinder extends UiBinder<Widget, LoginPageView> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;
    @UiField
    Button loginbutton;

    private IClientFactory factory;
    public LoginPageView(IClientFactory factory) {
        this.factory = factory;
        initWidget(uiBinder.createAndBindUi(this));
    }

    public String getName(){
        return username.getValue();
    }

    public String getPassword(){
        return password.getText();
    }

    @UiHandler("loginbutton")
    void handleClick(ClickEvent e) {
        factory.getUserServiceAsync().loginUser(getName(),getPassword(), new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error:"+caught.getLocalizedMessage());
            }

            @Override
            public void onSuccess(String result) {
                MyConstants string = GWT.create(MyConstants.class);
                if (result==null) {
                    factory.getLogger().log(Level.SEVERE, "User:" + getName()+" entered invalid pass");
                    Window.alert(string.invalidPassword());
                }
                else {
                    factory.getLogger().log(Level.SEVERE, "User " + getName()+" logged in");
                    factory.getEventBus().fireEvent(new LoginEvent(result));
                }
            }
        });

    }


}