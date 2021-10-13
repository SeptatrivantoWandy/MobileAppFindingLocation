package com.example.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class LocationDetailForm extends AppCompatActivity {

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";
    public static final String SEND_ATTRACTION_ID = "com.example.mcslecproject.SEND_ATTRACTION_ID";

    public static Vector<BookmarkData> BDV = new Vector<>();

    public static Vector<BookmarkDBHelper> Bdb_helper_vector = new Vector<>();

    int userId;

    UserData obj_UD;
    BookmarkDBHelper BDdb;
    UserDataDBHelper UDdb;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeForm.class);

        intent.putExtra(SEND_ID, userId);

        startActivity(intent);
        finish();

        super.onBackPressed();
    }


    ImageView atPhotoImg;
    TextView atNameTV;
    TextView atDescTV;
    TextView atLocTV;
    TextView atSchTV;
    TextView atPhoneTV;
    TextView atWebTV;

    Button showLocation;
    Button addToBookmark;

    String atId;
    int atImage;
    String atName;
    String atDescription;
    String atLocationStreet;
    String atSchedule;
    String atPhone;
    String atWebsite;

    String userName;
    String userEmail;
    String userPhoneNumber;
    String userPassword;
    String userDOB;
    String userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail_form);

        BDdb = new BookmarkDBHelper(LocationDetailForm.this);
        UDdb = new UserDataDBHelper(LocationDetailForm.this);

        atPhotoImg = findViewById(R.id.attractionImageDetail);
        atNameTV = findViewById(R.id.textViewAttractionNameDetail);
        atDescTV = findViewById(R.id.textViewAttractionDescriptionDetail);
        atLocTV = findViewById(R.id.textViewAttractionLocationDetail);
        atSchTV = findViewById(R.id.textViewAttractionScheduleDetail);
        atPhoneTV = findViewById(R.id.textViewAttractionPhoneDetail);
        atWebTV = findViewById(R.id.textViewAttractionWebsiteDetail);
        showLocation = findViewById(R.id.buttonShowLocation);
        addToBookmark = findViewById(R.id.buttonBookmarkAttraction);

        Intent intent = getIntent();

        atId = intent.getStringExtra("id");
        atImage = intent.getIntExtra("foto", -1);
        atName = intent.getStringExtra("nama");
        atDescription = intent.getStringExtra("deskripsi");
        atLocationStreet = intent.getStringExtra("alamat");
        atSchedule = intent.getStringExtra("jadwal");
        atPhone = intent.getStringExtra("telp");
        atWebsite = intent.getStringExtra("website");

        userId = intent.getIntExtra("userid", -1);

        userName = MainActivity.UDV.get(userId).getUserName();
        userEmail = MainActivity.UDV.get(userId).getUserEmail();
        userPhoneNumber = MainActivity.UDV.get(userId).getUserPhoneNumber();
        userPassword = MainActivity.UDV.get(userId).getUserPassword();
        userDOB = MainActivity.UDV.get(userId).getUserDOB();
        userGender = MainActivity.UDV.get(userId).getUserGender();

        atPhotoImg.setImageResource(atImage);
        atNameTV.setText(atName);
        atDescTV.setText(atDescription);
        atLocTV.setText(atLocationStreet);
        atSchTV.setText(atSchedule);
        atPhoneTV.setText(atPhone);
        atWebTV.setText(atWebsite);

        atDescTV.setMovementMethod(new ScrollingMovementMethod());
        atLocTV.setMovementMethod(new ScrollingMovementMethod());
        atWebTV.setMovementMethod(new ScrollingMovementMethod());

        addToBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeForm.class);

                BDdb = new BookmarkDBHelper(LocationDetailForm.this);
                BDdb.insertBD(new BookmarkData("1", userId+1+"", atId+""));
                Bdb_helper_vector.add(BDdb);

                BDV.clear();
                storeBookmarkDataInVector();


                Toast.makeText(getApplicationContext(), "location bookmarked", Toast.LENGTH_SHORT).show();

                intent.putExtra(SEND_ID, userId);
                startActivity(intent);
                finish();

            }
        });

        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GmapsLocation.class);
                intent.putExtra(SEND_ID, userId);
                intent.putExtra(SEND_ATTRACTION_ID, atId);


                startActivity(intent);

            }
        });

    }

    public int storeBookmarkDataInVector () {
        Cursor cursor = BDdb.readAllBookmarkData();
        if(cursor.getCount() == 0) {

            return -1;

        } else {


            while (cursor.moveToNext()) {

                BookmarkData obj =  new BookmarkData(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                BDV.add(obj);
            }
            return 1;
        }
    }

}