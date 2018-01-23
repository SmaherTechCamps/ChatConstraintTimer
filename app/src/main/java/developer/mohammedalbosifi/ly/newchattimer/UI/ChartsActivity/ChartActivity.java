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


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chart);
//        mBarChart = (BarChart) findViewById(R.id.mBarChart);
//        mPieChart = (PieChart) findViewById(R.id.mPieChart);
//        onCreate2();
//    }

    @AfterViews
    public void onCreate2() {
        setTitle("المخطط البياني");
        List<AmountOfRunTime> am = dbContext.getAppDao().getAppListRunTime();
         Float minutes=0.6f;
        txt.setText("");
        for (int i = 0; i < am.size(); i++) {
            minutes=(am.get(i).getSecondCount()/60f);

            txt.append(am.get(i).getAppName()+"    بمعدل "+minutes+"ساعة"+"\n");
            mPieChart.addPieSlice(new PieModel(am.get(i).getAppName(),minutes, Color.parseColor(am.get(i).getAppColor())));

        }

        mPieChart.startAnimation();

//        mBarChart.startAnimation();


    }

}
