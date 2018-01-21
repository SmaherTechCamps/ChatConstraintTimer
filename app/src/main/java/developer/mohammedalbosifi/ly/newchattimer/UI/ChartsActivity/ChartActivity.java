package developer.mohammedalbosifi.ly.newchattimer.UI.ChartsActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;



import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
 import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import developer.mohammedalbosifi.ly.newchattimer.R;

 public class ChartActivity extends AppCompatActivity  {
      BarChart mBarChart;
      PieChart mPieChart;

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_chart);
         mBarChart=(BarChart)findViewById(R.id.mBarChart);
         mPieChart=(PieChart)findViewById(R.id.mPieChart);
         onCreate2();
     }


     public void onCreate2() {

         mPieChart.addPieSlice(new PieModel("Freetime", 15, Color.parseColor("#FE6DA8")));
         mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
         mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
         mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));
         mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));
         mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));
         mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));

         mPieChart.startAnimation();



        mBarChart.addBar(new BarModel(2.3f, 0xFF123456));
        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
        mBarChart.addBar(new BarModel(3.3f, 0xFF563456));
        mBarChart.addBar(new BarModel(1.1f, 0xFF873F56));
        mBarChart.addBar(new BarModel(2.7f, 0xFF56B7F1));
        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
        mBarChart.addBar(new BarModel(0.4f, 0xFF1FF4AC));
        mBarChart.addBar(new BarModel(4.f,  0xFF1BA4E6));

        mBarChart.startAnimation();






    }

}
