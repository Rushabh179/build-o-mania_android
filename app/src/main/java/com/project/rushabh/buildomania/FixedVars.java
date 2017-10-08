package com.project.rushabh.buildomania;

/**
 * Created by Rushabh on 08-Oct-17.
 */

class FixedVars {
    // File name for storing shared preferences
    public static final String PREF_NAME = "tokenInfo";

    // User name (make variable public to access from outside)
    public static final String PREF_USER_NAME = "userName";

    // Token received from server (make variable public to access from outside)
    public static final String PREF_LOGIN_TOKEN = "loginToken";

    //Password entered by the user
    public static final String PREF_USER_PASSWORD = "password";

    //The base URL for server connection
    public static final String BASE_URL = "https://build-o-mania.herokuapp.com";

    public static final String FONT_NAME = "ExoRegular.ttf";

    public static Boolean SENTFROMSTARTPAGE = false ;

    public static Boolean signedUp  = false;

    public static Boolean loggedIn = false;
}
