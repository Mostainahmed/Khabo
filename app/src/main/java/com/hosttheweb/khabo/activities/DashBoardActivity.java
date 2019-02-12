package com.hosttheweb.khabo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.fragments.ContactUsFragment;
import com.hosttheweb.khabo.fragments.MenuFragment;

public class DashBoardActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment selectedFragment = null;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = MenuFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomcontent, selectedFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    selectedFragment = MenuFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomcontent, selectedFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    selectedFragment = MenuFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomcontent, selectedFragment).commit();
                    return true;
                case R.id.navigation_information:
                    selectedFragment = MenuFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomcontent, selectedFragment).commit();
                    return true;
                case R.id.navigation_contact_us:
                    selectedFragment = ContactUsFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomcontent, selectedFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.bottomcontent, MenuFragment.newInstance()).commit();
    }

}
