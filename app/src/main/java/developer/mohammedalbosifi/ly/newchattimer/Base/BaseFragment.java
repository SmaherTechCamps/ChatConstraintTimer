package developer.mohammedalbosifi.ly.newchattimer.Base;

import android.arch.persistence.room.Room;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.greenrobot.eventbus.EventBus;

import developer.mohammedalbosifi.ly.newchattimer.Application.AppInstanse;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;

/**
 * Created by Mohammed_Albosifi on 23/10/17.
 */
@EFragment
public abstract class BaseFragment extends Fragment {

    @App
    protected AppInstanse appInstanse;
    protected AppDataBase dbContext;

    @AfterViews
    protected void onCreate() {
        dbContext= Room.databaseBuilder(getContext().getApplicationContext(),AppDataBase.class,"chat_db").allowMainThreadQueries().build();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDetach() {
        dbContext=null;
        appInstanse.clear();
        appInstanse =null;
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }



    @UiThread
    public void showToast(String... parms){
        if(parms[0]!=""){
            switch (parms[1]){
                case "e":
                    MDToast.makeText(getContext(),parms[0], Toast.LENGTH_LONG,MDToast.TYPE_ERROR).show();
                    break;
                case "s":
                    MDToast.makeText(getContext(),parms[0],Toast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();
                    break;
                case "i":
                    MDToast.makeText(getContext(),parms[0],Toast.LENGTH_LONG,MDToast.TYPE_INFO).show();
                    break;
                case "w":
                    MDToast.makeText(getContext(),parms[0],Toast.LENGTH_LONG,MDToast.TYPE_WARNING).show();
                    break;
                default:
                    MDToast.makeText(getContext(),parms[0],Toast.LENGTH_LONG).show();
            }
        }
    }

    public void showSnakBar(View view, String message)
    {
        if(message!=null){
            {
                Snackbar.make(view,message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }}
    }



    public int getInt(String val){ return Integer.parseInt(val);}

    public String getStr(int val){
        return String.valueOf(val);
    }

    public boolean isEmpty(EditText myeditText) {return myeditText.getText().toString().trim().length() == 0;}

    public boolean isEmpty(AutoCompleteTextView myAutoCompleteTextView) {
        return myAutoCompleteTextView.getText().toString().trim().length() == 0;
    }
    public boolean isEmpty(TextView myTextView) {return myTextView.getText().toString().trim().length() == 0;}






}
