package developer.mohammedalbosifi.ly.newchattimer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AppServices extends Service {
    public AppServices() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
