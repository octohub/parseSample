/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize Parse
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        Parse.initialize(this, "jlwm1E9q8whwwnbF4FtfP0XGawBo3xJEaxJAsdl1", "BwsVpIEOr7qbCcwU7IGsWfFdndWmFq0GylWah6AD");

        /*
        Enabling Automatic User so that ParseUser.getCurrentUser() is never null
        The user will automatically be created in the cloud the first time the user or any object
        with a relation to the user is saved. Until that point, the user's object ID will be null.
         */
        ParseUser.enableAutomaticUser();

        /*
        We increment run count so that Parse talks to the server and automatically creates the user.
        If we did not do this, we would have to wait for the user to subscribe or unsubscribe from a
        class before they were created and visible on our Parse dashboard.
         */
        ParseUser.getCurrentUser().increment("RunCount"); //so first time it is run

        /*
        This will actually save our increment command we did above to the Parse server. Until we do the
        saveInBackground, the user will not show up in our parse server dashboard.
         */
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) { //If e != null, this means something went wrong

                } else { //Save was successful

                }
            }
        });

        /*
        These three lines below are for enabling push notifications. We don't need the
         "parseInstallation.put("user",ParseUser.getCurrentUser());" line to enable push notifications,
         but what this does is associate our installation with the specific user created on this device.
         */
        ParseInstallation parseInstallation = ParseInstallation.getCurrentInstallation();
        parseInstallation.put("user", ParseUser.getCurrentUser());
        parseInstallation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) { //If e != null, this means something went wrong

                } else { //Save was successful

                }
            }
        });

    }
}
