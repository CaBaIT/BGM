package com.example.azubi.bgm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azubi.bgm.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_übersicht:
//                    setTitle("Baugeräte Übersicht");
                    ItemFragment fragment = new ItemFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout,fragment)
                            .commit();
                    return true;
                case R.id.navigation_ausgang:
//                    setTitle("Baugeräte Ausgang");
                    AusgangFragment fragment1 = new AusgangFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout,fragment1)
                            .commit();
                    return true;
                case R.id.navigation_eingang:
//                    setTitle("Baugeräte Eingang");
                    EingangFragment fragment2 = new EingangFragment();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout,fragment2)
                            .commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            ItemFragment firstFragment = new ItemFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frameLayout, firstFragment);
            ft.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this,"Gerät angeklickt",Toast.LENGTH_LONG).show();
    }
}
