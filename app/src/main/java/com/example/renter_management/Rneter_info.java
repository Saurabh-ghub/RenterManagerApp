package com.example.renter_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Rneter_info extends AppCompatActivity {
   private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
TextView setName,setPhone,setAddress;
    ImageView setPhotoID;
    Button download_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rneter_info);
        MyDatabase db = MyDatabase.getInstance(this);



if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
    // Permission is not granted, request it
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
            PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
}

if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
    // Permission is not granted, request it
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
}


        int r_id = getIntent().getIntExtra("id", 0);
        Renter renter = db.getRenter(r_id);

        setName = findViewById(R.id.set_rname);
        setPhone = findViewById(R.id.set_rphone);
        setAddress = findViewById(R.id.set_raddress);
//        download_btn = findViewById(R.id.download_id_btn);
//        setPhotoID = findViewById(R.id.id_img);

        Uri imageUri = renter.getPhoto();

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//        != PackageManager.PERMISSION_GRANTED) {
//    ActivityCompat.requestPermissions(this,
//            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//            PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
//} else {
//    // Permission has been granted, read the image
//    try {
//        InputStream inputStream = getContentResolver().openInputStream(imageUri);
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//        ImageView imageView = findViewById(R.id.id_img);
//        imageView.setImageBitmap(bitmap);
//    } catch (FileNotFoundException e) {
//        // Handle the exception
//        Log.e("Exception", "File not found: " + e.getMessage());
//    } catch (IOException e) {
//        // Handle the exception
//        Log.e("Exception", "Error reading file: " + e.getMessage());
//    }
//}

//        Bitmap bmp = null;
//        try {
//            bmp = getBitmapFromUri(photoUri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        setPhotoID.setImageBitmap(bmp);
        setName.setText(renter.getRenter_name());
        setPhone.setText(renter.getPhone());
        setAddress.setText(renter.getAddres());
//        download_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(Rneter_info.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(Rneter_info.this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
//                } else {
//            // Permission has already been granted, you can read the file
//                    try {
//                        InputStream inputStream = getContentResolver().openInputStream(photoUri);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    // Read from the input stream
//        }
//
//            }
//        });
//        try {
//            Bitmap image = getBitmapFromUri(photoUri);
//            setPhotoID.setImageBitmap(image);
//
//        } catch (IOException e) {
//            Log.e("PhotoUriException",e.toString());
//            e.printStackTrace();
//        }


    }
//    public Bitmap getBitmapFromUri(Uri uri) throws IOException {
//        Bitmap image = null;
//        if (ContextCompat.checkSelfPermission(Rneter_info.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(Rneter_info.this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
//                } else {
//            // Permission has already been granted, you can read the file
//            ParcelFileDescriptor parcelFileDescriptor =
//                    getContentResolver().openFileDescriptor(uri, "r");
//            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
//             image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
//            parcelFileDescriptor.close();
//
//        }
//        return image;
//    }
    public void downloadImage(Uri uri, File file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(uri);
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
