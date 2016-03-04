package com.myApp.gwt.client.internationalizaton;

/**
 * Created by Fedir on 03.03.2016.
 */
import com.google.gwt.i18n.client.Constants;

public interface MyConstants extends Constants {
    @DefaultStringValue("Good morning, ")
    String goodMorning();
    @DefaultStringValue("Good evening, ")
    String goodEvening();
    @DefaultStringValue("Good night, ")
    String goodNight();
    @DefaultStringValue("Good day, ")
    String goodDay();
    @DefaultStringValue("User: ")
    String user();
    @DefaultStringValue("Password: ")
    String password();
    @DefaultStringValue("Login")
    String login();
    @DefaultStringValue("Invalid username or password")
    String invalidPassword();
    @DefaultStringValue("Exit")
    String exit();
}