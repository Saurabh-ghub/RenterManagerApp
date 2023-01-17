package com.example.renter_management;

import static android.provider.MediaStore.Images.Media.insertImage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import org.apache.commons.io.IOUtils;
//import java.io.InputStream;

public class RenterDetails extends AppCompatActivity {
    //  Button
    Button BSelectImage, save_detail;
    EditText renter_name,phone,address;
     Uri imgdata;
    MyDatabase db;
    // One Preview Image
    ImageView IVPreviewImage;

    int flatID ;
    // constant to compare
      // the activity result code
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_details);
        flatID = getIntent().getIntExtra("flatID",0);
        db = MyDatabase.getInstance(this);
        // register the UI widgets with their appropriate IDs
        BSelectImage = findViewById(R.id.take_btn);
        IVPreviewImage = findViewById(R.id.img_view);
        renter_name = findViewById(R.id.edit_rentername);
        phone = findViewById(R.id.edit_phone);
        address = findViewById(R.id.edit_renteraddres);
        save_detail = findViewById(R.id.save_renter_btn);

        // handle the Choose Image button to trigger
          // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        //When save btn is clicked
        save_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = renter_name.getText().toString();
                String phone_no = phone.getText().toString();
                String addr = address.getText().toString();
                Renter renter = new Renter(-1,name,phone_no,addr,imgdata,flatID);
                db.addRenter(renter);
            }
        });

    }
    // this function is triggered when
      // the Select Image Button is clicked
   private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

ActivityResultLauncher<Intent> launchSomeActivity
	= registerForActivityResult(
		new ActivityResultContracts
			.StartActivityForResult(),
		result -> {
			if (result.getResultCode()
				== Activity.RESULT_OK) {
				Intent data = result.getData();
				// do your operation from here....
				if (data != null
					&& data.getData() != null) {
					Uri selectedImageUri = data.getData();
                    imgdata = selectedImageUri;

                    Log.d("image",selectedImageUri.toString());
					Bitmap selectedImageBitmap;
					try {
						selectedImageBitmap
							= MediaStore.Images.Media.getBitmap(
								this.getContentResolver(),
								selectedImageUri);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        selectedImageBitmap.compress(Bitmap.CompressFormat.PNG,50,stream);

                        IVPreviewImage.setImageBitmap(
						selectedImageBitmap);
					}
					catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});

}