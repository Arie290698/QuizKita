package com.ilkom.quizkita;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Notif extends Service {
    public Notif() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
