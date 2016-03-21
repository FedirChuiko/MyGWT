package com.myApp.gwt.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.myApp.gwt.client.IClientFactory;
import com.myApp.gwt.client.ITimeProvider;
import com.myApp.gwt.client.internationalizaton.MyConstants;
import com.myApp.gwt.client.widget.IHomePageView;

import java.util.logging.Level;

/**
 * Created by Fedir on 16.03.2016.
 */
public class HomePagePresenter extends PagePresenter implements Presenter {

    public IHomePageView view;
    public ITimeProvider timeProvider;


    public HomePagePresenter(IClientFactory factory, IHomePageView view, ITimeProvider timeProvider) {
        super(factory);
        this.timeProvider = timeProvider;
        this.view = view;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;


    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    private String getGreetingMessage() {
        MyConstants string = GWT.create(MyConstants.class);
        int hours = timeProvider.getCurrentHour();
        if (isBetween(hours, 6, 8)) {
            return string.goodMorning();
        } else if (isBetween(hours, 9, 18)) {
            return string.goodDay();
        } else if (isBetween(hours, 19, 22)) {
            return string.goodEvening();
        } else
            return string.goodNight();
    }

    @Override
    public void go(HasWidgets container) {
        view.setGreetings(getGreetingMessage() + firstName);
        container.add(view.asWidget());
        factory.getLogger().log(Level.SEVERE, getGreetingMessage() + firstName + " - says application");
    }
}
