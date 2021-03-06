package developer.mohammedalbosifi.ly.newchattimer.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Mohammed_Albosifi on 17/12/2017.
 */
@Database(entities = {ChatEntity.class,AmountOfRunTime.class},version = 2,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{
    public abstract AppDao getAppDao();
}
