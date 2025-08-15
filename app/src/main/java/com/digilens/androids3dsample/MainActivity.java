package com.digilens.androids3dsample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private static Context context;
    private static final String TAG = "3DImage";

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
            switch (permsRequestCode) {
                case 200:
                    break;
            }
            super.onRequestPermissionsResult(permsRequestCode, permissions, grantResults);
        }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        context = getApplicationContext();

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {"android.permission.READ_EXTERNAL_STORAGE"};

            int permsRequestCode = 200;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(perms, permsRequestCode);
            }
        }

        if (imageView == null) {
            Log.e(TAG, "null imageview handle");
        } else {
            InputStream resource;
            resource = getResources().openRawResource(R.drawable.sample_s3d);
            imageView.setImageBitmap(BitmapFactory.decodeStream(resource));
        }
    }
    @Override
    protected void onPause() {
        imageView.setVisibility(View.GONE);
        super.onPause();
        Log.e(TAG, "ASJ: Pausing now");
    }
    @Override
    protected void onResume() {
        super.onResume();
        imageView.setVisibility(View.VISIBLE);
        Log.e(TAG, "ASJ: Resuming now");
    }
}