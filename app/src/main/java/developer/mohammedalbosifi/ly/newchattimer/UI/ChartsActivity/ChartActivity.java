package developer.mohammedalbosifi.ly.newchattimer.UI.ChartsActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import developer.mohammedalbosifi.ly.newchattimer.Base.BaseActivity;
import developer.mohammedalbosifi.ly.newchattimer.DataBase.AmountOfRunTime;
import developer.mohammedalbosifi.ly.newchattimer.R;

@EActivity(R.layout.activity_chart)
public class ChartActivity extends BaseActivity {
//    BarChart mBarChart;
    @ViewById
    TextView txt;
    @ViewById
    PieChart mPieChart;



    @AfterViews
    public void onCreate2() {
        setTitle("المخطط البياني");
        List<AmountOfRunTime> am = dbContext.getAppDao().getAppListRunTime();
         Float minutes,minutes2=0.0f;
        txt.setText("");
        for (int i = 0; i < am.size(); i++) {
            minutes=(am.get(i).getSecondCount()/60f);
            minutes2=(am.get(i).getSecondCount()/60f*30f);

            txt.append("  \n  "+" بمعدل "+minutes+" يوميا "+am.get(i).getAppName());
            txt.append("  \n  "+"و بمعدل "+minutes2+" شهريا "+am.get(i).getAppName());
            mPieChart.addPieSlice(new PieModel(am.get(i).getAppName(),minutes, Color.parseColor(am.get(i).getAppColor())));


        }

        mPieChart.startAnimation();



    }

}
