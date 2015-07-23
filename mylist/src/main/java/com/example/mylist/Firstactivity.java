package com.example.mylist;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Firstactivity extends AppCompatActivity implements ItemFragment.OnFragmentInteractionListener {

    public static  String  lf="listfragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstactivity);
        Fragment listfragment=getFragmentManager().findFragmentByTag(lf);
        if (null==listfragment){
            getFragmentManager().beginTransaction()
                    .add(R.id.frame_layout,ItemFragment.newInstance("cz1", "cz2"), lf).commit();
        }else {
            getFragmentManager().beginTransaction()
                   .replace(R.id.frame_layout,listfragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_firstactivity, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(Firstactivity.this, id, Toast.LENGTH_SHORT).show();

    }
}
