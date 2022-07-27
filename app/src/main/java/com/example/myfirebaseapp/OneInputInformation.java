package com.example.myfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OneInputInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_input_information);

        Intent intent = getIntent();
        ImageView image = (ImageView) findViewById(R.id.imv_platform_logo_3);
        TextView text1 = (TextView) findViewById(R.id.tv_login_prompt_2);
        EditText et1 = (EditText) findViewById(R.id.et_login_prompt_2);

        switch (intent.getStringExtra("platform")) {

            case "email": {
                image.setBackgroundResource(R.drawable.email_logo);
                text1.setText(R.string.email_prompt);
                et1.setHint(R.string.email_hint);
                break;
            }
            case "phone": {
                image.setBackgroundResource(R.drawable.phone_logo);
                text1.setText(R.string.phone_prompt);
                et1.setHint(R.string.phone_hint);
                break;
            }
        }

        Button button1 = (Button) findViewById(R.id.btn_login_back1);
        Button button2 = (Button) findViewById(R.id.btn_login_ok1);
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
                EditText text1 = (EditText) findViewById(R.id.et_login_prompt_2);
                String text = text1.getText().toString();
                Intent intent2 = new Intent(view.getContext(), DisplayLoginInformation.class);
                intent2.putExtra("username", text);

                switch (intent.getStringExtra("platform")) {

                    case "email": {
                        intent2.putExtra("From_Activity", "email");
                        break;
                    }
                    case "phone": {
                        intent2.putExtra("From_Activity", "phone");
                        break;
                    }
                }

                if (text.isEmpty()){
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
