package com.myApp.gwt.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.myApp.gwt.client.IClientFactory;
import com.myApp.gwt.client.event.LoginEvent;
import com.myApp.gwt.client.widget.ILoginPageView;

import java.util.logging.Level;

/**
 * Created by Fedir on 16.03.2016.
 */
public class LoginPagePresenter extends PagePresenter implements Presenter, ILoginPageView.Presenter {

    public ILoginPageView view;

    public LoginPagePresenter(IClientFactory factory, ILoginPageView view) {
        super(factory);
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }

    @Override
    public void onLoginButtonClicked(final String username, final String password) {
        factory.getUserServiceAsync().loginUser(username, password, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                view.alert(caught.getLocalizedMessage());
            }
            @Override
            public void onSuccess(String result) {
                if (result == null) {
                    factory.getLogger().log(Level.SEVERE, "User:" + username + " entered invalid pass");
                    view.showErrorMessage();
                } else {
                    factory.getLogger().log(Level.SEVERE, "User " + username + " logged in");
                    factory.getEventBus().fireEvent(new LoginEvent(result));
                }
            }
        });
    }
}
