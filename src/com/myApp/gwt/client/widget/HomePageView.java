package com.myApp.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by Fedir on 24.02.2016.
 */
public class HomePageView extends Composite implements IHomePageView {

    @Override
    public String getGreetings() {
        return this.greetings.getText();
    }

    @Override
    public void setGreetings(String greetings) {
        this.greetings.setText(greetings);
    }

    interface MyUiBinder extends UiBinder<Widget, HomePageView> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Label greetings;

    @UiField
    Anchor exitlink;


    public HomePageView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}