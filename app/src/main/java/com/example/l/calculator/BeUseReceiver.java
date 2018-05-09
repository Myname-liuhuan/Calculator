package com.example.l.calculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BeUseReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        if(intent.getAction().equals("StartCalculator")){
            Intent start = new Intent(context,MainActivity.class);
            start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(start);
        }
    }
}
