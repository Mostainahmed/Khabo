package com.hosttheweb.khabo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView userName, userNumber, userLocataion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userName = findViewById(R.id.user_name);
        userLocataion = findViewById(R.id.user_location);
        userNumber = findViewById(R.id.user_number);

        userName.setText(getIntent().getStringExtra("NAME"));
        userNumber.setText(getIntent().getStringExtra("NUMBER"));
        userLocataion.setText(getIntent().getStringExtra("LOCATION"));
    }
}
