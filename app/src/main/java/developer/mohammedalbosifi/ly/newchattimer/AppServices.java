package developer.mohammedalbosifi.ly.newchattimer;

import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

import developer.mohammedalbosifi.ly.newchattimer.Application.AppInstanse;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.ChatEntity;
import developer.mohammedalbosifi.ly.newchattimer.UI.ChatList.ChatListItem;

@EService
public class AppServices extends Service {


    AppDataBase dbContext;


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbContext = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "chat_db").allowMainThreadQueries().build();

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
            if (isRun(ce.getAppName())){
                Toast.makeText(this, ce.getAppName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, ce.getAppName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, ce.getAppName(), Toast.LENGTH_SHORT).show();
            }
        }
     }

    @Override
    public void onDestroy() {
        dbContext = null;
        super.onDestroy();
    }


    public boolean isRun(String packName) {
        String packageName = "";
        boolean isRun = false;
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            packageName = packageInfo.packageName;
            if (packageInfo.packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            } else if (packageName.contains(packName)) {
                isRun = true;
            }
        }
        return isRun;

    }
}

