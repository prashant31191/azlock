package com.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

/**
 * A broadcast receiver who listens for incoming SMS
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "=====sms broadcast receiver--onReceive--======");
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            Log.d(TAG, "=====sms if-1-SmsRetriever.SMS_RETRIEVED_ACTION-======");



            // Simple message integrate
            String smsSender = "";
            String smsBody = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsBody += smsMessage.getMessageBody();

                }

            }


            Log.d(TAG, "SMS detected: From " + smsSender + " With text " + smsBody);
            Toast.makeText(context, "#00# BroadcastReceiver caught conditional SMS: " + smsBody, Toast.LENGTH_LONG).show();


            String strKey1 = "Seeka", strKey2 = "OTP";

            if (smsBody.contains(strKey1) && smsBody.contains(strKey2) && smsBody.contains(" ")) {
                Log.d(TAG, "BroadcastReceiver caught conditional SMS: ==SMS_CONDITION==" + smsBody);
                String strOTPCode [] = smsBody.split(" ");

                Log.d(TAG, "#00# Your OTP Code is: " + strOTPCode[0]);


                Toast.makeText(context, "#00# Your OTP Code is: " + strOTPCode[0] , Toast.LENGTH_LONG).show();
            }
        }
        else if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {

            Log.d(TAG, "=====sms if-1-SmsRetriever.SMS_RETRIEVED_ACTION-======");

            String smsBody = "";
            String strKey1 = "Seeka", strKey2 = "OTP";

            // for the google api
            Bundle extras = intent.getExtras();
            Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch(status.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    smsBody = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    Log.d(TAG, "BroadcastReceiver caught conditional SMS: " + smsBody);

                    Toast.makeText(context, "#11# BroadcastReceiver caught conditional SMS: " + smsBody, Toast.LENGTH_LONG).show();


                   // String strKey1 = "Seeka", strKey2 = "OTP";

                    if (smsBody.contains(strKey1) && smsBody.contains(strKey2) && smsBody.contains(" ")) {
                        Log.d(TAG, "#11# BroadcastReceiver caught conditional SMS: ==SMS_CONDITION==" + smsBody);

                        String strOTPCode [] = smsBody.split(" ");
                        Toast.makeText(context, "#11# Your OTP Code is: " + strOTPCode[0] , Toast.LENGTH_LONG).show();
                    }
                    break;
                case CommonStatusCodes.TIMEOUT:
                    Log.d(TAG, "BroadcastReceiver caught conditional SMS---TIMEOUT--- ");

                    break;
            }
        }
        else
        {

        }
    }
}


   /* @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            String smsSender = "";
            String smsBody = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsBody += smsMessage.getMessageBody();
                }

            }

            if (smsBody.startsWith(SmsHelper.SMS_CONDITION)) {
                Log.d(TAG, "Sms with condition detected");
                Toast.makeText(context, "BroadcastReceiver caught conditional SMS: " + smsBody, Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, "SMS detected: From " + smsSender + " With text " + smsBody);
        }
    }












    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {
        try {
            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null) {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String smsMessageStr = "";
                for (int i = 0; i < sms.length; ++i) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    String smsBody = smsMessage.getMessageBody().toString();
                    String address = smsMessage.getOriginatingAddress();

                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";
                }
                Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

                //this will update the UI with message
                ActMyMessages inst = ActMyMessages.instance();
                inst.updateList(smsMessageStr);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


    */
