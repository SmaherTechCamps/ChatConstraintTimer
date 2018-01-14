package developer.mohammedalbosifi.ly.newchattimer.UI.ChartsActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

import developer.mohammedalbosifi.ly.newchattimer.R;

public class ChatView extends AppCompatActivity {

    TextView txt;
    Button btn;
    ActivityManager activityManager;
    List<ActivityManager.RunningTaskInfo> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        txt=(TextView)findViewById(R.id.txt);
        btn=(Button)findViewById(R.id.btn);
        aa();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             aa();
            }
        });
    }
    public void aa(){



    }


}