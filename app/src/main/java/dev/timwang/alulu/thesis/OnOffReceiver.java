package dev.timwang.alulu.thesis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OnOffReceiver extends BroadcastReceiver {

    public boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)
                || intent.getAction().equals(Intent.ACTION_USER_PRESENT)
                || intent.getAction().equals(Intent.ACTION_USER_UNLOCKED)
        ) {
            wasScreenOn = true;
        }
    }
}
