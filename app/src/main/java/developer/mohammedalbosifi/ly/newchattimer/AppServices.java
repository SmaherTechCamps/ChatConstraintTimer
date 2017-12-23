package developer.mohammedalbosifi.ly.newchattimer;

import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;

import developer.mohammedalbosifi.ly.newchattimer.Application.AppInstanse;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.ChatEntity;

@EService
 public class AppServices extends Service {
    public AppServices() {
    }

      AppDataBase dbContext;


     @Override
    public IBinder onBind(Intent intent) {
         throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbContext = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "chat_db").allowMainThreadQueries().build();
        dbContext.getAppDao().insertApp(new ChatEntity("facebook",1000,10,10,10));
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    tt();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        return START_STICKY;
    }


    @UiThread
    public void tt(){
        Toast.makeText(AppServices.this, dbContext.getAppDao().getAppList().get(0).getAppName(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onDestroy() {
        dbContext = null;
        super.onDestroy();
    }
}
