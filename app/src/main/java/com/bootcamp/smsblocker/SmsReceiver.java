package com.bootcamp.smsblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle= intent.getExtras();
        if (bundle != null) {
            Object[] smsObjects = (Object[])bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[smsObjects.length];
            StringBuilder sms = new StringBuilder();
            for (int index = 0; index < smsObjects.length; index++) {
                messages[index] = SmsMessage.createFromPdu((byte[]) smsObjects[index]);
                sms.append(messages[index].getMessageBody());
            }
            String sender = messages[0].getOriginatingAddress();
            String message = sender+" "+sms.toString();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            abortBroadcast();
        }
    }


}
