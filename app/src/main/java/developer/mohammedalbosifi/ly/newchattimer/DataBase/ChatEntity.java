package developer.mohammedalbosifi.ly.newchattimer.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Mohammed_Albosifi on 17/12/2017.
 */

@Entity(tableName = "ChatEntity")
public class ChatEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    private String appName;

    @ColumnInfo
    private int secondCount;

    @ColumnInfo
    private int hour,minth,second;

    public ChatEntity(String appName, int secondCount, int hour, int minth, int second) {
        this.appName = appName;
        this.secondCount = secondCount;
        this.hour = hour;
        this.minth = minth;
        this.second = second;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(int secondCount) {
        this.secondCount = secondCount;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinth() {
        return minth;
    }

    public void setMinth(int minth) {
        this.minth = minth;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
