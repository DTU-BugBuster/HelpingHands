package com.helpinghands.broacastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.helpinghands.constants.AppConstant;
import com.helpinghands.service.ShakeListenerService;
import com.helpinghands.utils.Logger;

public class RecreateShakeServiceReceiver extends BroadcastReceiver {

    private static final String TAG = RecreateShakeServiceReceiver.class.getSimpleName();

    public RecreateShakeServiceReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Logger.d(TAG, intent.getAction() + " called broadcast receiver");

        if (intent.getAction().equalsIgnoreCase(AppConstant.RECREATE_SHAKE_SERVICE_INTENT)) {


            // restart shake listener service in background
            Intent shakeIntent = new Intent(context, ShakeListenerService.class);
            context.startService(shakeIntent);

        }
    }
}
