package com.example.shazam.groceryhelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RewardsActivity extends AppCompatActivity {

    private Camera camera;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button captureButton = (Button)findViewById(R.id.captureImage);
        camera = getCameraInstance();
        CameraPreview cameraPreview = new CameraPreview(this, camera);
        FrameLayout previewScreen = (FrameLayout)findViewById(R.id.frameLayout);
        previewScreen.addView(cameraPreview);

        imageView = (ImageView)findViewById(R.id.cardView);

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "GroceryHelper");
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_CARD" + ".jpg");
        imageView.setImageURI(Uri.fromFile(mediaFile));

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, pictureCallback);
            }
        });
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // Could not get camera.
        }
        return camera;
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, final Camera camera) {
            final File pictureFile = getOutputMediaFile();
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            }
            catch (Exception e) {
                Toast.makeText(RewardsActivity.this, "Error saving file", Toast.LENGTH_SHORT).show();
            }
            camera.stopPreview();
            //Toast.makeText(RewardsActivity.this, "Picture location = " + pictureFile.getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
            Log.d("Picture", pictureFile.getAbsolutePath().toString());
            Log.d("Picture", " " + pictureFile.exists());
            //Log.d("HAHA", " " + Uri.fromFile(pictureFile));
            imageView.setImageURI(Uri.fromFile(pictureFile));
        }
    };


    //Gets the photo from the phone storage
    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "GroceryHelper");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("GroceryHelper", "Failed to create directory");
                return null;
            }
        }

        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_CARD" + ".jpg");

        return mediaFile;
    }
}
