package developer.mohammedalbosifi.ly.newchattimer.UI.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import developer.mohammedalbosifi.ly.newchattimer.AppServices;
import developer.mohammedalbosifi.ly.newchattimer.AppServices_;
import developer.mohammedalbosifi.ly.newchattimer.R;
import developer.mohammedalbosifi.ly.newchattimer.UI.ChatList.EventMessage;
import developer.mohammedalbosifi.ly.newchattimer.Utils.Utility;

@EActivity(R.layout.activity_main2)
public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById  Toolbar toolbar;
    @ViewById FloatingActionButton fab;
    @ViewById DrawerLayout drawer;
    @ViewById NavigationView navigationView;
    @AfterViews
    protected void afterView() {

         setSupportActionBar(toolbar);

        setTitle("قائمة برامج الدردشة");
        if (Utility.isServiceRunning(getApplicationContext(),AppServices_.class)){
            Toast.makeText(this, "yes ", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "yes ", Toast.LENGTH_SHORT).show();
        }else {
            startService(new Intent(Main2Activity.this,AppServices_.class));
        }

         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView.setNavigationItemSelectedListener(this);
    }


    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
         if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
             if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
             {
                 super.onBackPressed();
                 return;
             }
             else {
                 Toast.makeText(getBaseContext(), "أضـغـط مرتين للـخروج", Toast.LENGTH_SHORT).show();
             }

             mBackPressed = System.currentTimeMillis();
         }



     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            EventBus.getDefault().postSticky(new EventMessage(true));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

         drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
