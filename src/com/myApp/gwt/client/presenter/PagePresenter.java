package com.myApp.gwt.client.presenter;

import com.myApp.gwt.client.IClientFactory;

/**
 * Created by Fedir on 16.03.2016.
 */
public abstract class PagePresenter implements Presenter {
    protected IClientFactory factory;

    protected PagePresenter(IClientFactory factory) {
        this.factory = factory;
    }
}
