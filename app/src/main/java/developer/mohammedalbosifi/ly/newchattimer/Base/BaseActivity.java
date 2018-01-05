package developer.mohammedalbosifi.ly.newchattimer.Base;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import developer.mohammedalbosifi.ly.newchattimer.Application.AppInstanse;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDataBase;
import developer.mohammedalbosifi.ly.newchattimer.R;

/**
 * Created by Mohammed_Albosifi on 22/10/17.
 */

@EActivity
public abstract class BaseActivity extends AppCompatActivity {


    @App
    protected AppInstanse appInstanse;
    protected AppDataBase dbContext;


    @AfterViews
    protected void onCreate() {
        dbContext = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "chat_db").allowMainThreadQueries().build();
    }

    @Override
    protected void onDestroy() {
        dbContext = null;
        appInstanse.clear();
        appInstanse = null;
        super.onDestroy();
    }


    @UiThread
    public void showToast(String... parms) {
        if (parms[0] != "") {
            switch (parms[1]) {
                case "e":
                    MDToast.makeText(this, parms[0], Toast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                    break;
                case "s":
                    MDToast.makeText(this, parms[0], Toast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                    break;
                case "i":
                    MDToast.makeText(this, parms[0], Toast.LENGTH_LONG, MDToast.TYPE_INFO).show();
                    break;
                case "w":
                    MDToast.makeText(this, parms[0], Toast.LENGTH_LONG, MDToast.TYPE_WARNING).show();
                    break;
                case "d":
                    Toast.makeText(this, parms[0], Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    public void showSnakBar(View view, String message) {
        if (message != null) {
            {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }

    public void KeyBoardUtils(boolean type, EditText et) {
        if (type) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(
                    et.getWindowToken(), 0);
        } else {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et,
                    InputMethodManager.SHOW_IMPLICIT);
        }

    }

    public int getInt(String val) {
        return Integer.parseInt(val);
    }

    public String getStr(int val) {
        return String.valueOf(val);
    }

    public boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public boolean isEmpty(AutoCompleteTextView myAutoCompleteTextView) {
        return myAutoCompleteTextView.getText().toString().trim().length() == 0;
    }

    public boolean isEmpty(TextView myTextView) {
        return myTextView.getText().toString().trim().length() == 0;
    }

    public void showLovleyDialog(String... parms) {
        if (parms[2] == "e") {
            new LovelyStandardDialog(this)
                    .setTopColorRes(R.color.RED)
                    .setButtonsColorRes(R.color.RED_DARK)
                    .setIcon(R.drawable.ic_loop)
                    .setTitle(parms[1])
                    .setMessage(parms[0])
                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }
        if (parms[2] == "s") {
            new LovelyStandardDialog(this)
                    .setTopColorRes(R.color.LIGHT_GREEN)
                    .setButtonsColorRes(R.color.GREEN)
                    .setIcon(R.drawable.ic_loop)
                    .setTitle(parms[1])
                    .setMessage(parms[0])
                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }

        if (parms[3] == "i") {
            new LovelyStandardDialog(this)
                    .setTopColorRes(R.color.INFO)
                    .setButtonsColorRes(R.color.BLUE)
                    .setIcon(R.drawable.ic_info_white_24dp)
                    .setTitle(parms[1])
                    .setMessage(parms[0])
                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }
    }

}
