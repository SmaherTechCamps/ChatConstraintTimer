package developer.mohammedalbosifi.ly.newchattimer.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Mohammed_Albosifi on 21/01/2018.
 */
@Entity(tableName = "AmountOfRunTime")
public class AmountOfRunTime {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String appName;

    @ColumnInfo
    private String appColor;

    @ColumnInfo
    private int secondCount;


    public AmountOfRunTime(String appName, String appColor, int secondCount) {
        this.appName = appName;
        this.appColor = appColor;
        this.secondCount = secondCount;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppColor() {
        return appColor;
    }

    public void setAppColor(String appColor) {
        this.appColor = appColor;
    }

    public int getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(int secondCount) {
        this.secondCount = secondCount;
    }
}
