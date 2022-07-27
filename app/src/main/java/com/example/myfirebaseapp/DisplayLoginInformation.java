package com.example.myfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayLoginInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_login_information);

        Intent intent = getIntent();
        ImageView image = (ImageView) findViewById(R.id.imv_login_platform_logo);

        String username = "";
        String password = "";
        switch (intent.getStringExtra("From_Activity")) {

            case ("Twitter"): {
                username = getString(R.string.username) + " " + intent.getStringExtra("username");
                password = getString(R.string.password) + " " + intent.getStringExtra("password");
                image.setBackgroundResource(R.drawable.twitter_logo);
                break;
            }
            case ("Facebook"): {
                username = getString(R.string.username) + " " + intent.getStringExtra("username");
                password = getString(R.string.password) + " " + intent.getStringExtra("password");
                image.setBackgroundResource(R.drawable.facebook_logo);
                break;
            }
            case ("email"): {
                username = getString(R.string.email_login_page) + " " + intent.getStringExtra("username");
                image.setBackgroundResource(R.drawable.email_logo);
                break;
            }
            case ("phone"): {
                username = getString(R.string.phone_number) + " " + intent.getStringExtra("username");
                image.setBackgroundResource(R.drawable.phone_logo);
                break;
            }
            case ("Google"): {
                username = getString(R.string.gmail) + " " + intent.getStringExtra("username");
                password = getString(R.string.password) + " " + intent.getStringExtra("password");
                image.setBackgroundResource(R.drawable.google_logo);
                break;
            }
        }

        TextView textView1 = (TextView) findViewById(R.id.tv_user_login_information);
        TextView textView2 = (TextView) findViewById(R.id.tv_user_login_password);
        textView1.setText(username);
        textView2.setText(password);

        Button button1 = (Button) findViewById(R.id.btn_back_to_login_page);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}