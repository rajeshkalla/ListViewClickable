package com.tecnics.listviewclickable;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class reminder extends Service {
    public reminder() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(), "This is a Service!", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }
}
