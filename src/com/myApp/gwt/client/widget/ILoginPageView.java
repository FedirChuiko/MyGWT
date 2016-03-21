package com.myApp.gwt.client.widget;

/**
 * Created by Fedir on 16.03.2016.
 */
public interface ILoginPageView extends IPageView {


    public interface Presenter {
        void onLoginButtonClicked(String username, String password);

    }

    void setPresenter(Presenter presenter);

    public void showErrorMessage();
}
