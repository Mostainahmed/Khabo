package com.hosttheweb.khabo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userName, userPhone, userPassword, othersLocation;
    private TextView loginText;
    private Spinner userLocation;
    private int locationValue;
    private Button signUp;
    private String otherslocation, finallocation;
    private static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Initialization
        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_pass);
        userPhone = findViewById(R.id.user_phone);
        signUp = findViewById(R.id.signup_btn);
        othersLocation = findViewById(R.id.otherslocation);
        userLocation = findViewById(R.id.user_location);
        loginText = findViewById(R.id.logintext);

        //Initializing ApiInterface
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        //"Already signed up" textview listener
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        //Signup button listener
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Animation for bouncy button
                final Animation myAnim = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.bounce);
                MyBounceInterPolator interpolator = new MyBounceInterPolator(0.05, 20);
                myAnim.setInterpolator(interpolator);
                v.startAnimation(myAnim);

                //Getting all the user data to variables
                String username = userName.getText().toString();
                String password = userPassword.getText().toString();
                String usernumber = userPhone.getText().toString();
                String userlocation = userLocation.getSelectedItem().toString();

                //Checking if the user selects others for their location. If so "Otherlocation" EditText will be visible to them
                if(userlocation.equals("Others")){
                    othersLocation.setVisibility(View.VISIBLE);
                    otherslocation = othersLocation.getText().toString();
                }else{
                    othersLocation.setVisibility(View.GONE);
                }

                //Sending all the user values to servers, Starting from here(Sending point).
                if(inputsAreCorrect(username, password, usernumber, userlocation, otherslocation)){
                    if(userlocation.equals("Others") && !otherslocation.equals("")){
                        finallocation = otherslocation;
                    }else{
                        finallocation = userlocation;
                    }
                    startActivity(new Intent(RegistrationActivity.this, OrderActivity.class));
                }
            }
        });
    }

    //Method for checking the correct input
    private boolean inputsAreCorrect(String name, String password, String number, String location, String otherslocation) {
        if (name.isEmpty()) {
            userName.setError("Please enter a name");
            userName.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            userPassword.setError("Please enter password of at most 10 characters");
            userPassword.requestFocus();
            return false;
        }

        if (number.isEmpty()) {
            userPhone.setError("Please enter a valid number");
            userPhone.requestFocus();
            return false;
        }

        if (location.equals("Select Location")) {
            userLocation.requestFocus();
            return false;
        }
        if (location.equals("Others")) {

            if (otherslocation.isEmpty()) {
                othersLocation.requestFocus();
                return false;
            }
        }


        return true;
    }
    public void performRegistration(){
        
    }
}
