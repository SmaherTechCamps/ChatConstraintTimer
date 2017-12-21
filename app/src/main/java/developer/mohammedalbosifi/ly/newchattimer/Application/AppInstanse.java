package developer.mohammedalbosifi.ly.newchattimer.Application;

import android.app.Application;
import android.content.Context;

import org.androidannotations.annotations.EApplication;

import developer.mohammedalbosifi.ly.newchattimer.Utils.AnimationUtil;
import developer.mohammedalbosifi.ly.newchattimer.Utils.Utility;


/**
 * Created by Mohammed_Albosifi on 23/11/2017.
 */
@EApplication
public class AppInstanse extends Application {

    AnimationUtil animationUtil;
    Utility utility;
    Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void clear(){
        animationUtil=null;
        utility=null;
        appContext=null;
    }



    public synchronized AnimationUtil getAnimationUtil() {
        if (animationUtil==null){
            animationUtil=new AnimationUtil();
        }
        return animationUtil;
    }

    public synchronized Utility getUtility() {
        if (utility==null){
            utility=new Utility();
        }
        return utility;
    }

}
