package com.skel.appskeletonv4.domain.common;

public interface Constants {

    //TAGS to identify fragments
    String NAV_TAG_PROFILE ="Profile";
    String NAV_TAG_HOME = "Home";
    String NAV_TAG_RECENTS = "recents";


    //Fragment tags
    String FRAGMENT_PACKAGE_LIST = "PackageList";
    String FRAGMENT_PACKAGE_SERVICE_LIST = "PackageServiceList";
    String FRAGMENT_PACKAGE_SELECTION= "PackageSelection";
    String FRAGMENT_PACKAGE_BUY = "PackageBuyFragment";
    String FRAGMENT_PACKAGE_DETAILS= "PackageDetailsFragment";


    String YEAR_AGO="y";
    String DAYS_AGO="d";
    String WEEKS_AGO="w";
    String MONTHS_AGO ="mn";
    String HOURS_AGO = "hr";
    String MINUTES_AGO="min";
    String SECONDS_AGO = "sec";

    String EMPTY = "empty";


    String REFERRAL_TYPE_REGULAR_NAME = "Regular Customer"; //1
    String REFERRAL_TYPE_WALKING_NAME = "Walking Customer"; //2
    String REFERRAL_TYPE_CALL_NAME = "Converted By Call"; //3
    String REFERRAL_TYPE_ADS_NAME = "Converted From Ads"; //4

    String CUSTOMER_TYPE_PACKAGE_NAME = "Package"; //1
    String CUSTOMER_TYPE_REGULAR_NAME ="Regular Customer"; //2
    String CUSTOMER_TYPE_WALKING_NAME  = "Walking Customer"; //3

    int CUSTOMER_TYPE_PACKAGE = 1; //1
    int CUSTOMER_TYPE_REGULAR = 2; //2
    int CUSTOMER_TYPE_WALKING  = 3; //3

    //Integers numbers
    int REFERRAL_TYPE_REGULAR = 1; //1
    int REFERRAL_TYPE_WALKING = 2; //2
    int REFERRAL_TYPE_CALL = 3; //3
    int REFERRAL_TYPE_ADS = 4; //4


    String CUSTOMER_ID = "CustomerId";
    String PACKAGE_ID = "PackageId";
    String PACKAGE_AMOUNT = "PackageAmount";
    String PACKAGE_NAME = "PackageName";


    int PACKAGE_NORMAL_LIST = 1;
    int PACKAGE_SELECTION_TYPE = 2;


}
