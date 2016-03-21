package com.example.cz.myapplication;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Outline;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * base activity contains four
 */
public class MainActivity extends AppCompatActivity {

    public static String TAG = "TAG";

    private TextView tetx;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private Toolbar toolbar;

    private kaoshiFragment mkaoshiFragment = null;
    private LeftMenuFragment mLeftMenuFragment = null;

    private ImageButton button;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inittoolbar();
        initview();
        /*fragment  这样添加 是因为
        当屏幕旋转  oncreate  方法会再次调用  防止new  出来多个fragment
         */
        FragmentManager fm = getSupportFragmentManager();


        mLeftMenuFragment = (LeftMenuFragment) fm.findFragmentByTag("leftmenu");


        if (mLeftMenuFragment == null) {
            mLeftMenuFragment = new LeftMenuFragment();
            fm.beginTransaction().add(R.id.left_menu, mLeftMenuFragment, "leftmenu").commit();
        }
        addFragment(1);
        button= (ImageButton) findViewById(R.id.add_button);
        button.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int shapsize= (int) getResources().getDimension(R.dimen.shape_size);
                outline.setRoundRect(0,0,shapsize,shapsize,shapsize/2);
            }
        });
        button.setClipToOutline(true);


    }

    public void  addFragment(int item){
        FragmentManager fm = getSupportFragmentManager();
        mkaoshiFragment = (kaoshiFragment) fm.findFragmentByTag("kaoshi");
        if (mkaoshiFragment == null) {
            mkaoshiFragment = new kaoshiFragment();
            fm.beginTransaction().add(R.id.main_content, mkaoshiFragment, "kaoshi").commit();
        }

    }


    private void initview() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void inittoolbar() {
        tetx = (TextView) findViewById(R.id.action_settings);
        toolbar = (Toolbar) findViewById(R.id.id_tool);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("成绩查询");
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar kaoshi_item_view clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        VpnService
        return super.onOptionsItemSelected(item);
    }
}
