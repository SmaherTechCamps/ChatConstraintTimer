package developer.mohammedalbosifi.ly.newchattimer;

import android.app.ActivityManager;
import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.widget.Toast;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

 import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.ChatEntity;

@EService
public class AppServices extends Service {


    AppDataBase dbContext;
    ActivityManager activityManager ;
    List<ActivityManager.RunningTaskInfo> tasks;
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        dbContext = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "chat_db").allowMainThreadQueries().build();
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

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
    public void tt() {
        List<ChatEntity> chatEntities=new ArrayList<>();
            chatEntities=dbContext.getAppDao().getAppList();
        for (ChatEntity ce:chatEntities){
            isRun(ce.getAppName());
         }
     }

    @Override
    public void onDestroy() {
        dbContext = null;
        super.onDestroy();
    }

    @UiThread
    public void isRun(String appName) {
        for (int i = 0; i < tasks.size(); i++) {
            Toast.makeText(this, tasks.get(i).baseActivity.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, tasks.get(i).baseActivity.toString(), Toast.LENGTH_SHORT).show();
            if (tasks.get(i).baseActivity.toString().contains(appName)){
                ChatEntity ce=dbContext.getAppDao().getApp(appName);
                ce.setSecondCount(ce.getSecondCount()-1);
                dbContext.getAppDao().updateApp(ce);
                Toast.makeText(this,dbContext.getAppDao().getApp(appName).getSecondCount()+"" , Toast.LENGTH_SHORT).show();
            }
        }
     }
}

