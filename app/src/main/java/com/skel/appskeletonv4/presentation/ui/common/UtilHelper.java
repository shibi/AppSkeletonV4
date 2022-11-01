package com.skel.appskeletonv4.presentation.ui.common;

import com.skel.appskeletonv4.domain.common.Constants;

public class UtilHelper {

    public static String getReferralTypeNameStringFrom(Integer value){
        try {

            switch (value)
            {
                case Constants.REFERRAL_TYPE_REGULAR:

                    return Constants.REFERRAL_TYPE_REGULAR_NAME;

                case Constants.REFERRAL_TYPE_WALKING:
                    return Constants.REFERRAL_TYPE_WALKING_NAME;

                case Constants.REFERRAL_TYPE_CALL:
                    return Constants.REFERRAL_TYPE_CALL_NAME;

                case Constants.REFERRAL_TYPE_ADS:
                    return Constants.REFERRAL_TYPE_ADS_NAME;
            }

            return "Other";

        }catch (Exception e){
            e.printStackTrace();
            return "NOT SET";
        }
    }

    public static String getCustomerTypeNameFrom(Integer value){
        try {

            switch (value)
            {
                case 1:
                    return Constants.CUSTOMER_TYPE_PACKAGE_NAME;

                case 2:
                    return Constants.CUSTOMER_TYPE_REGULAR_NAME;

                case 3:
                    return Constants.CUSTOMER_TYPE_WALKING_NAME;
            }

            return "Other";

        }catch (Exception e){
            e.printStackTrace();
            return "NOT SET";
        }
    }


    public static String getReferralTypeNumberFromName(String str_referraltype_name){
        try {

            switch (str_referraltype_name)
            {
                case Constants.REFERRAL_TYPE_REGULAR_NAME:

                    return ""+Constants.REFERRAL_TYPE_REGULAR;

                case Constants.REFERRAL_TYPE_WALKING_NAME:
                    return ""+Constants.REFERRAL_TYPE_WALKING;

                case Constants.REFERRAL_TYPE_CALL_NAME:
                    return ""+Constants.REFERRAL_TYPE_CALL;

                case Constants.REFERRAL_TYPE_ADS_NAME:
                    return ""+Constants.REFERRAL_TYPE_ADS;
            }

            return "0";

        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     *
     * */
    public static String getCustomerPackageTypeNumberFromName(String str_cus_packtype_name){
        try {

            switch (str_cus_packtype_name)
            {
                case Constants.CUSTOMER_TYPE_PACKAGE_NAME:
                    return ""+Constants.CUSTOMER_TYPE_PACKAGE;

                case Constants.CUSTOMER_TYPE_REGULAR_NAME:
                    return ""+Constants.CUSTOMER_TYPE_REGULAR;

                case Constants.CUSTOMER_TYPE_WALKING_NAME:
                    return ""+Constants.CUSTOMER_TYPE_WALKING;
            }

            return "0";

        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

}
