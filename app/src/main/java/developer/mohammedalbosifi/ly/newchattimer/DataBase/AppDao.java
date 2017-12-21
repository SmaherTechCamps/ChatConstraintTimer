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


}
