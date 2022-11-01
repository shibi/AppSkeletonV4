package com.skel.appskeletonv4.domain.common;


import com.skel.appskeletonv4.domain.common.utils.Utility;

public interface Config {

    /**
     * App version
     * */
    String APP_VERSION = "1.0";


    /**
     * url prefix
     * */
    //String BASE_URL_PREFIX = "http://dev.appbaseurl.com/";
    //String BASE_URL_PREFIX = "https://reqres.in/";
    //String BASE_URL_PREFIX = "http://spa.dxgsofts.in/";
    String BASE_URL_PREFIX = "http://devkc.dxgsofts.in/";


    /**
     * base url for api calls
     * */
    String BASE_URL = BASE_URL_PREFIX+"api/";


    /**
     * API key normal, edit api key here.
     * */
    String API_KEY = "@Spa_ApiKey0123#";


    /**
     * DO NOT EDIT.....
     * Encrypted API key, used in network request for security
     * Do not edit this ..  edit only API_KEY variable above.
     * */
    String ENCRYPTED_API_KEY = Utility.base64Encrypt(Config.API_KEY);


    /**
     * To enable or disable log
     * */
    boolean IS_DEVELOPMENT = true;

    /**
     * Enable log for tracing request links
     * */
    boolean LOG = Config.IS_DEVELOPMENT;

    /**
     * Enable log for tracing request links
     * */
    String DB_NAME = "AppLocalDB#SPaMAn";


    /**
     * shared preference local storage name
     * */
    String SHARED_PREF_NAME ="SpaManage#012";


    /**
     * http client connection read , write  and time out in seconds
     * */
    int HTTP_CONNECTION_TIMEOUT_IN_SEC = 60;
    int HTTP_CONNECTION_READ_TIMEOUT_IN_SEC = 60;
    int HTTP_CONNECTION_WRITE_TIMEOUT_IN_SEC = 60;


    int SPLASH_TIMEOUT = 0;

    String SCAN_ID_PREFIX = "AGS";

}


