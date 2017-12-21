package developer.mohammedalbosifi.ly.newchattimer.UI.ChatConstraintSetting;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import developer.mohammedalbosifi.ly.newchattimer.Base.BaseActivity;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AppDao;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.ChatEntity;
import developer.mohammedalbosifi.ly.newchattimer.R;
import developer.mohammedalbosifi.ly.newchattimer.UI.ChatList.EventMessage;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;

@EActivity(R.layout.activity_chat_constraint)
public class ChatConstraintActivity extends BaseActivity {

    @ViewById
    TextView tvAppName;

    @ViewById
    TextView tvMints, tvHour, tvSecond;

    @ViewById
    ImageView ivImage;
    @ViewById
    StepperTouch stepperTouch, stepperTouch2, stepperTouch3;
    @ViewById
    Button btnSave;
    String appName, appName2;


    @AfterViews
    protected void onCreate() {
        super.onCreate();
        Bundle bundle = getIntent().getExtras();

        tvAppName.setText("أسم التطبيق  : " + bundle.getString("appPackage") + " / " + bundle.getString("appName"));
        appName = bundle.getString("appPackage");
        appName2 = bundle.getString("appName");
        String isSetting = bundle.getString("isSetting");
        setTitle(bundle.getString("appName"));

        if (appName.contains("facebook.orca")) {
            ivImage.setImageResource(R.drawable.ic_facebook2);
        } else if (appName.contains("whatsapp")) {
            ivImage.setImageResource(R.drawable.ic_whatsapp);
        } else if (appName.contains("instagram")) {
            ivImage.setImageResource(R.drawable.ic_instagram);
        } else if (appName.contains("facebook.lite")) {
            ivImage.setImageResource(R.drawable.ic_facebook_lite);
        } else if (appName.contains("twitter")) {
            ivImage.setImageResource(R.drawable.ic_twitter);
        } else if (appName.contains("viber")) {
            ivImage.setImageResource(R.drawable.ic_viber);
        } else if (appName.contains("skype")) {
            ivImage.setImageResource(R.drawable.ic_skype);
        } else if (appName.contains("telegram")) {
            ivImage.setImageResource(R.drawable.ic_telegram);
        } else if (appName.contains("line")) {
            ivImage.setImageResource(R.drawable.ic_line);
        }

        if (dbContext.getAppDao().getApp(appName2) != null) {
            stepperTouch.stepper.setValue(dbContext.getAppDao().getApp(appName2).getHour());
            stepperTouch2.stepper.setValue(dbContext.getAppDao().getApp(appName2).getMinth());
            stepperTouch3.stepper.setValue(dbContext.getAppDao().getApp(appName2).getSecond());
        }

        stepperTouch.stepper.setMin(0);
        stepperTouch.stepper.setMax(24);
        stepperTouch.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int i, boolean b) {
                if (i < 10) {
                    tvHour.setText("0" + i);
                } else {
                    tvHour.setText("" + i);
                }
            }
        });

        stepperTouch2.stepper.setMin(0);
        stepperTouch2.stepper.setMax(59);
        stepperTouch2.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int i, boolean b) {
                if (i < 10) {
                    tvMints.setText("0" + i);
                } else {
                    tvMints.setText("" + i);
                }
            }
        });

        stepperTouch3.stepper.setMin(0);
        stepperTouch3.stepper.setMax(59);
        stepperTouch3.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int i, boolean b) {
                if (i < 10) {
                    tvSecond.setText("0" + i);
                } else {
                    tvSecond.setText("" + i);
                }
            }
        });
    }


    @Click
    public void btnSave() {
        int timeSum = getInt(tvHour.getText().toString()) * 60 * 60 + getInt(tvMints.getText().toString()) * 60 + getInt(tvSecond.getText().toString());

        AppDao appDao = dbContext.getAppDao();
        if (timeSum > 0) {
            if (appDao.getApp(appName2) != null) {
                appDao.deleteApp(appDao.getApp(appName2));
            }
            appDao.insertApp(new ChatEntity(appName2, timeSum, getInt(tvHour.getText().toString()), getInt(tvMints.getText().toString()), getInt(tvSecond.getText().toString())));
            showToast("تم حفظ البيانات بنجاح", "d");
        } else {
            if (appDao.getApp(appName2) != null) {
                appDao.deleteApp(appDao.getApp(appName2));
            }
        }
        EventBus.getDefault().postSticky(new EventMessage(true));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mm, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.goBack) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
