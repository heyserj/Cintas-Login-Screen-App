package com.example.myfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_information);

        Intent intent = getIntent();
        ImageView image = (ImageView) findViewById(R.id.imv_platform_logo_2);
        TextView text1 = (TextView) findViewById(R.id.tv_login_prompt_1);
        EditText et1 = (EditText) findViewById(R.id.et_login_prompt_1);
        EditText et2 = (EditText) findViewById(R.id.et_login_prompt_2);

        switch (intent.getStringExtra("platform")) {

            case "Google": {
                image.setBackgroundResource(R.drawable.google_logo);
                text1.setText(R.string.google_prompt);
                et1.setHint(R.string.google_hint);
                et2.setHint(R.string.hint_password);
                break;
            }
            case "Facebook": {
                image.setBackgroundResource(R.drawable.facebook_logo);
                text1.setText(R.string.facebook_prompt);
                et1.setHint(R.string.hint_username);
                et2.setHint(R.string.hint_password);
                break;
            }
            case "Twitter": {
                image.setBackgroundResource(R.drawable.twitter_logo);
                text1.setText(R.string.twitter_prompt);
                et1.setHint(R.string.hint_username);
                et2.setHint(R.string.hint_password);
                break;
            }
        }

        Button button1 = (Button) findViewById(R.id.btn_login_back);
        Button button2 = (Button) findViewById(R.id.btn_login_ok);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText text1 = (EditText) findViewById(R.id.et_login_prompt_1);
                EditText password_et = (EditText) findViewById(R.id.et_login_prompt_2);
                String text = text1.getText().toString();
                String pass = password_et.getText().toString();
                Intent intent2 = new Intent();
                intent2.putExtra("username", text);
                intent2.putExtra("password", pass);

                switch (intent.getStringExtra("platform")) {

                    case "Google": {
                        intent2.putExtra("From_Activity", "Google");
                        break;
                    }
                    case "Facebook": {
                        intent2.putExtra("From_Activity", "Facebook");
                        break;
                    }
                    case "Twitter": {
                        intent2.putExtra("From_Activity", "Twitter");
                        break;
                    }
                }

                if (text.isEmpty() || pass.isEmpty()){
                    setResult(3, intent2);
                }
                else{
                    setResult(2, intent2);
                }
                finish();
            }
        });
    }
}
