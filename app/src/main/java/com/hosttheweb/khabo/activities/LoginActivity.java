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
import android.widget.TextView;
import android.widget.Toast;

import com.hosttheweb.khabo.api.ApiClient;
import com.hosttheweb.khabo.api.ApiInterface;
import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.model.User;
import com.hosttheweb.khabo.others.MyBounceInterPolator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;
    private EditText userName, userPassword;
    private TextView signUpText;
    private Button signInButton;
    private static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_password);
        signUpText = findViewById(R.id.signuptext);
        signInButton = findViewById(R.id.signup_btn);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progressBar = findViewById(R.id.progressbar);
        linearLayout = findViewById(R.id.login_activity_layout);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation bouncy
                final Animation myAnim = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.bounce);
                MyBounceInterPolator interpolator = new MyBounceInterPolator(0.05, 20);
                myAnim.setInterpolator(interpolator);
                v.startAnimation(myAnim);

                performLogin();
                alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }
    public void performLogin(){
        linearLayout.setAlpha(0.5f);
        progressBar.setVisibility(View.VISIBLE);
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();

        Call<User> call = apiInterface.performLogin(name, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){

                    linearLayout.setAlpha(1f);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    String name = response.body().getName();
                    String number = response.body().getNumber();
                    String location = response.body().getLocation();

                    Intent userIntent = new Intent(LoginActivity.this, OrderActivity.class);
//
                    userIntent.putExtra("NAME", name);
                    userIntent.putExtra("NUMBER", number);
                    userIntent.putExtra("LOCATION", location);

                    startActivity(userIntent);
//                    startActivity(new Intent(LoginActivity.this, OrderActivity.class));
                }else if(response.body().getResponse().equals("failed")){
                    linearLayout.setAlpha(1f);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Login failed... Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                linearLayout.setAlpha(1f);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

















