package com.hosttheweb.khabo.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.hosttheweb.khabo.api.ApiClient;
import com.hosttheweb.khabo.api.ApiInterface;
import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.model.User;
import com.hosttheweb.khabo.others.MyBounceInterPolator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;
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
        progressBar = findViewById(R.id.progressbar);
        linearLayout = findViewById(R.id.registration_activity_layout);

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

                //AlertDialouge to show
                alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();

                //Getting all the user data to variables
                String username = userName.getText().toString();
                String password = userPassword.getText().toString();
                String usernumber = userPhone.getText().toString();
                String userlocation = userLocation.getSelectedItem().toString();
                otherslocation = othersLocation.getText().toString();

                //Sending all the user values to servers, Starting from here(Sending point).
                if(inputsAreCorrect(username, password, usernumber, userlocation, otherslocation)){
//                    if(userlocation.equals("Others") && !otherslocation.equals("")){
//                        finallocation = otherslocation;
//                    }else{
//                        finallocation = userlocation;
//                    }
                    linearLayout.setAlpha(0.5f);
                    progressBar.setVisibility(View.VISIBLE);
                    performRegistration(username,usernumber,userlocation,otherslocation,password);
                    //startActivity(new Intent(RegistrationActivity.this, OrderActivity.class));
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

        if (otherslocation.isEmpty()) {
            othersLocation.requestFocus();
            return false;
        }

        return true;
    }
    public void performRegistration(String name, String number, String location, String otherslocation, String password){
        Call<User> call = apiInterface.performRegistration(name,password,location,otherslocation,number);

        //Toast.makeText(RegistrationActivity.this, name + number + location+ otherslocation + password, Toast.LENGTH_LONG).show();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    //Toast.makeText(RegistrationActivity.this, "Successfull",Toast.LENGTH_LONG).show();
                    linearLayout.setAlpha(1f);
                    progressBar.setVisibility(View.GONE);
                    alertDialog.setMessage("Congrats. Registration Successful");
                    alertDialog.show();
//                    alertDialog.dismiss();
                }
                else if(response.body().getResponse().equals("exist")){
                    //Toast.makeText(RegistrationActivity.this, "User already exists",Toast.LENGTH_LONG).show();
                    linearLayout.setAlpha(1f);
                    progressBar.setVisibility(View.GONE);
                    alertDialog.setMessage("User already exists... ");
                    alertDialog.show();
//                    alertDialog.dismiss();
                }
                else if(response.body().getResponse().equals("error")){
                    //Toast.makeText(RegistrationActivity.this, "Big Problem",Toast.LENGTH_LONG).show();
                    linearLayout.setAlpha(1f);
                    progressBar.setVisibility(View.GONE);
                    alertDialog.setMessage("Something went wrong... Please try again later");
                    alertDialog.show();
//                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(RegistrationActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                linearLayout.setAlpha(1f);
                alertDialog.setMessage(t.toString());
                alertDialog.dismiss();
            }
        });
    }
}
