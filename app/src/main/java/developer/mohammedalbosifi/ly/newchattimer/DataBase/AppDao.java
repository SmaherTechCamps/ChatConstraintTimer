package developer.mohammedalbosifi.ly.newchattimer.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Mohammed_Albosifi on 17/12/2017.
 */
@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertApp(ChatEntity... chatEntities);

    @Delete
    void deleteApp(ChatEntity... chatEntities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateApp(ChatEntity... chatEntities);

    @Query("select * from ChatEntity where appName=:appName limit 1")
    ChatEntity getApp(String appName);

    @Query("select * from ChatEntity")
    List<ChatEntity> getAppList();

    @Query("delete from ChatEntity where appName=:appName ")
    void deleteAppByName(String appName);
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRunTimeApp(AmountOfRunTime... mountOfRunTime);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRunTimeApp(AmountOfRunTime... mountOfRunTime);

    @Query("select * from AmountOfRunTime")
    List<AmountOfRunTime> getAppListRunTime();

    @Query("select * from AmountOfRunTime where appName=:appName limit 1")
    AmountOfRunTime getAppRunTime(String appName);
}
