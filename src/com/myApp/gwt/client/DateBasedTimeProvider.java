package com.myApp.gwt.client;

import java.util.Date;

/**
 * Created by Fedir on 17.03.2016.
 */
public class DateBasedTimeProvider implements ITimeProvider {
    @Override
    public int getCurrentHour() {
        return new Date().getHours();
    }
}
