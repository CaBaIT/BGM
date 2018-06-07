package com.example.azubi.bgm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_übersicht:
                    setTitle("Baugeräte Übersicht");
                    AusgangFragment fragment = new AusgangFragment();
                    android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout,fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_ausgang:
                    setTitle("Baugeräte Ausgang");
                    AusgangFragment fragment1 = new AusgangFragment();
                    android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.frameLayout,fragment1);
                    ft1.commit();
                    return true;
                case R.id.navigation_eingang:
                    setTitle("Baugeräte Eingang");
                    EingangFragment fragment2 = new EingangFragment();
                    android.support.v4.app.FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.frameLayout,fragment2);
                    ft2.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
