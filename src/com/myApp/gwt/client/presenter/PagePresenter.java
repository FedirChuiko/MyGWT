package com.myApp.gwt.client.presenter;

import com.myApp.gwt.client.IClientFactory;
import com.myApp.gwt.client.widget.IPageView;

/**
 * Created by Fedir on 16.03.2016.
 */
public abstract class PagePresenter implements Presenter {
    protected IClientFactory factory;
    protected IPageView view;

    protected PagePresenter(IClientFactory factory) {
        this.factory = factory;
    }
}
