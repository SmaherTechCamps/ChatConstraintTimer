package developer.mohammedalbosifi.ly.newchattimer;

import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;

import developer.mohammedalbosifi.ly.newchattimer.Application.AppInstanse;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.ChatEntity;

public class AppServices extends Service {
    public AppServices() {
    }

      protected AppDataBase dbContext;


     @Override
    public IBinder onBind(Intent intent) {
         throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbContext = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "chat_db").allowMainThreadQueries().build();
        dbContext.getAppDao().insertApp(new ChatEntity("facebook",1000,10,10,10));

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        dbContext = null;
        super.onDestroy();
    }
}
