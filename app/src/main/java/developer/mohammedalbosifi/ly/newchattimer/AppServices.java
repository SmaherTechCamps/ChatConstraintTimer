package developer.mohammedalbosifi.ly.newchattimer;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

import br.com.goncalves.pugnotification.notification.PugNotification;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AmountOfRunTime;
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
                    CalcRunTime();
                    try {
                        Thread.sleep(60000);
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
        List<ChatEntity> chatEntities =dbContext.getAppDao().getAppList();
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

            ce = appDao.getApp(appName.toLowerCase());
            if (tasks.get(i).baseActivity.toString().toLowerCase().contains(appName.toLowerCase())) {
                secondCount2 = ce.getSecondCount2();
                ce.setSecondCount2(secondCount2 + 60);
//                Toast.makeText(this, ce.getSecondCount2() + "", Toast.LENGTH_SHORT).show();
                if (secondCount2 >= ce.getSecondCount()) {
                    PugNotification.with(this)
                            .load()
                            .title("مؤقت تطبيقات الشات")
                            .message("أنتهت المدة المحددة للتطبيق "+"   "+appName)
                            .smallIcon(R.drawable.pugnotification_ic_launcher)
                            .largeIcon(R.drawable.pugnotification_ic_launcher)
                            .flags(Notification.DEFAULT_ALL)
                            .simple()
                            .build();

                } else {

                    appDao.updateApp(ce);
                }
            }
        }

    }

    @UiThread
    public void CalcRunTime() {
        List<AmountOfRunTime> amountOfRunTimes = appDao.getAppListRunTime();
        AmountOfRunTime amountOfRunTime;
        tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
        try{
            for (int i = 0; i < amountOfRunTimes.size(); i++) {
                if (tasks.get(i).baseActivity.toString().toLowerCase().contains(amountOfRunTimes.get(i).getAppName().toLowerCase())) {
//                    Toast.makeText(this, amountOfRunTimes.get(i).getAppName(), Toast.LENGTH_SHORT).show();
                    amountOfRunTime= appDao.getAppRunTime(amountOfRunTimes.get(i).getAppName().toLowerCase());
                    amountOfRunTime.setSecondCount(amountOfRunTimes.get(i).getSecondCount()+1);
                    appDao.updateRunTimeApp(amountOfRunTime);
                }
            }
        }catch (Exception e){

        }

    }

}

