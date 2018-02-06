package com.example.feher.sms_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by feher on 2018. 02. 06..
 */

public class smsbroadcastr extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] tomb = null;
        String sender = "";
        String message = "";

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            tomb = new SmsMessage[pdus.length];

            for (int i = 0; i < pdus.length; i++) {
                tomb[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                if (i == 0) {
                    sender = tomb[i].getOriginatingAddress();
                }
                message = message + tomb[i].getMessageBody();
            }
        }

        Toast.makeText(context, "Küldő: " + sender + "Üzenet: " + message, Toast.LENGTH_LONG).show();

        abortBroadcast();
    }
}
