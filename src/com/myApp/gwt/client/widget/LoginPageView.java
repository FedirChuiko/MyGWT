package com.myApp.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

/**
 * Created by Fedir on 24.02.2016.
 */
public class LoginPageView extends Composite implements ILoginPageView, IGWTView {

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;
    @UiField
    Button loginbutton;
    @UiField
    Label invalidPassword;
    private Presenter presenter;

    public LoginPageView() {
        initWidget(uiBinder.createAndBindUi(this));
        invalidPassword.setVisible(false);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void showErrorMessage() {
        invalidPassword.setVisible(true);
    }

    @UiHandler("loginbutton")
    void handleClick(ClickEvent e) {
        presenter.onLoginButtonClicked(username.getValue(), password.getText());

    }

    @Override
    public void alert(String message) {
        CustomWidgets.alertWidget("Error", message).center();
    }

    interface MyUiBinder extends UiBinder<Widget, LoginPageView> {
    }
}