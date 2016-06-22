package com.example.anja.imagecamera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1; //waits to identify intent
    ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CAMERA");

        Button btnPhoto = (Button) findViewById(R.id.btnPhoto);
        photoView = (ImageView) findViewById(R.id.photoView);

        //Disable the button if the user has no camera
        if (!hasCamera())
            btnPhoto.setEnabled(false);
    }

    //Check if the user has a camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //intend to take a picture
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {  //Check if took an image and if we had errors
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data"); //returning the photo information and we're converting it to a bitmap
            photoView.setImageBitmap(photo);
        }
    }
}
