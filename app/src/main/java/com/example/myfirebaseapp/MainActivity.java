package com.example.myfirebaseapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createSocialAlert(View view) {
        String name = "";
        switch (view.getId()) {

            case (R.id.btn_google): {
                name = "Google";
                break;
            }
            case (R.id.btn_facebook): {
                name = "Facebook";
                break;
            }
            case (R.id.btn_twitter): {
                name = "Twitter";
                break;
            }
            case (R.id.btn_email): {
                name = "email";
                break;
            }
            case (R.id.btn_phone): {
                name = "your phone";
                break;
            }
        }
        sendSocialAlert(view, name);
    }

    public void sendSocialAlert(View view, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.sign_in_alert) + " " + name + "?");
        builder.setTitle("Attention!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(view.getContext(), LoginInformation.class);
                switch (name) {

                    case "Google": {
                        intent.putExtra("platform", "Google");
                        break;
                    }
                    case "Facebook": {
                        intent.putExtra("platform", "Facebook");
                        break;
                    }
                    case "Twitter": {
                        intent.putExtra("platform", "Twitter");
                        break;
                    }
                    case "email": {
                        intent = new Intent(view.getContext(), OneInputInformation.class);
                        intent.putExtra("platform", "email");
                        break;
                    }
                    case "your phone": {
                        intent = new Intent(view.getContext(), OneInputInformation.class);
                        intent.putExtra("platform", "phone");
                        break;
                    }
                }
                activityResultLaunch.launch(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 2) {
                        //then the user didn't leave a field blank
                        Intent data = result.getData();
                        //want to go to DisplayLoginInformation class
                        Intent next = new Intent(getApplicationContext(), DisplayLoginInformation.class);
                        next.replaceExtras(data);
                        startActivity(next);
                    }

                    else{
                        //then the user left a field blank
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        builder2.setMessage(getString(R.string.login_error));
                        builder2.setTitle("Error Found!");
                        builder2.setCancelable(false);
                        builder2.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog2 = builder2.create();
                        alertDialog2.show();
                    }
                }
            });
}
