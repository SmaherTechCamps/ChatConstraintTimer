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

import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDao;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.ChatEntity;

@EService
public class AppServices extends Service {

    int secondCount2 = 0;
    ChatEntity ce;
    AppDataBase dbContext;
    ActivityManager activityManager;
    List<ActivityManager.RunningTaskInfo> tasks;
    List<ActivityManager.RunningAppProcessInfo> procInfos;
    AppDao appDao;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        dbContext = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "chat_db").allowMainThreadQueries().build();
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        appDao = dbContext.getAppDao();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    tt();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        return START_STICKY;
    }


    public void tt() {
        List<ChatEntity> chatEntities = new ArrayList<>();
        chatEntities = dbContext.getAppDao().getAppList();
        if (chatEntities.size() > 0) {
            for (ChatEntity ce : chatEntities) {
                isRun(ce.getAppName());
            }
        }

    }

    @Override
    public void onDestroy() {
        dbContext = null;
        super.onDestroy();
    }

    @UiThread
    public void isRun(String appName) {
        tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).baseActivity.toString().toLowerCase().contains(appName.toLowerCase())) {
                ce = appDao.getApp(appName.toLowerCase());
                secondCount2 = ce.getSecondCount2();
                Toast.makeText(this, secondCount2+"", Toast.LENGTH_SHORT).show();
                ce.setSecondCount2(secondCount2 + 5);
                Toast.makeText(this, ce.getSecondCount2()+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, ce.getSecondCount()+"", Toast.LENGTH_SHORT).show();
                appDao.updateApp(ce);
                if (secondCount2 >= ce.getSecondCount()) {
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}

