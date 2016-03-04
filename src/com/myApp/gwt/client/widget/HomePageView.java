package com.myApp.gwt.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.myApp.gwt.client.IClientFactory;
import com.myApp.gwt.client.internationalizaton.MyConstants;


import java.util.Date;
import java.util.logging.Level;

/**
 * Created by Fedir on 24.02.2016.
 */
public class HomePageView extends Composite {

    interface MyUiBinder extends UiBinder<Widget, HomePageView> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Label greetings;

    @UiField
    Anchor exitlink;


    private IClientFactory factory;

    public HomePageView(IClientFactory factory) {
        this.factory = factory;
        initWidget(uiBinder.createAndBindUi(this));
    }
    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    public String getGreetingMessage (){
        MyConstants string = GWT.create(MyConstants.class);
        int hours = new Date().getHours();
        if (isBetween(hours, 6, 9)) {
            return string.goodMorning();
        }
        else if (isBetween(hours, 9, 19)) {
            return string.goodDay();
        }
        else if (isBetween(hours, 19, 23)) {
            return string.goodEvening();
        }
        else
            return string.goodNight();
    }



    public void init(String name) {
        this.greetings.setText(getGreetingMessage() + name);
        factory.getLogger().log(Level.SEVERE, getGreetingMessage() + name + " - says application");

    }
}