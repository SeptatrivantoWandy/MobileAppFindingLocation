package com.example.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class BookmarkDetailForm extends AppCompatActivity {

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";
    public static final String SEND_ATTRACTION_ID = "com.example.mcslecproject.SEND_ATTRACTION_ID";

    public static Vector<BookmarkData> BDV = new Vector<>();

    public static Vector<BookmarkDBHelper> Bdb_helper_vector = new Vector<>();

    int userId;

    UserData obj_UD;
    BookmarkDBHelper BDdb;
    UserDataDBHelper UDdb;

    ImageView atPhotoImg;
    TextView atNameTV;
    TextView atDescTV;
    TextView atLocTV;
    TextView atSchTV;
    TextView atPhoneTV;
    TextView atWebTV;

    Button showLocation;
    Button deleteBookmark;

    String bmId;

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
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), BookmarkForm.class);

        intent.putExtra(SEND_ID, userId);

        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_detail_form);

        BDdb = new BookmarkDBHelper(BookmarkDetailForm.this);
        UDdb = new UserDataDBHelper(BookmarkDetailForm.this);

        atPhotoImg = findViewById(R.id.bmAttractionImageDetail);
        atNameTV = findViewById(R.id.bmTextViewAttractionNameDetail);
        atDescTV = findViewById(R.id.bmTextViewAttractionDescriptionDetail);
        atLocTV = findViewById(R.id.bmTextViewAttractionLocationDetail);
        atSchTV = findViewById(R.id.bmTextViewAttractionScheduleDetail);
        atPhoneTV = findViewById(R.id.bmTextViewAttractionPhoneDetail);
        atWebTV = findViewById(R.id.bmTextViewAttractionWebsiteDetail);
        showLocation = findViewById(R.id.bmButtonShowLocation);
        deleteBookmark = findViewById(R.id.buttonBookmarkDelete);

        Intent intent = getIntent();

        int numAtId;

        bmId = intent.getStringExtra("id");
        numAtId = Integer.parseInt(intent.getStringExtra("wisataid"));
        atId = intent.getStringExtra("wisataid");
        userId = intent.getIntExtra("userid", -1);


        atImage = HomeForm.locations.get(numAtId).getAttractionImage();

        atName = HomeForm.locations.get(numAtId).getAttractionName();
        atDescription = HomeForm.locations.get(numAtId).getAttractionDescription();
        atLocationStreet = HomeForm.locations.get(numAtId).getAttractionLocationStreet();
        atSchedule = HomeForm.locations.get(numAtId).getAttractionSchedule();
        atPhone = HomeForm.locations.get(numAtId).getAttractionPhone();
        atWebsite = HomeForm.locations.get(numAtId).getAttractionWebsite();

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

        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GmapsLocation.class);
                intent.putExtra(SEND_ID, userId);
                intent.putExtra(SEND_ATTRACTION_ID, atId);


                startActivity(intent);

            }
        });

        deleteBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookmarkForm.class);

                intent.putExtra(SEND_ID, userId);

                BDdb.deleteBD(Integer.parseInt(bmId));
                Toast.makeText(getApplicationContext(), "bookmark deleted", Toast.LENGTH_SHORT).show();

                startActivity(intent);
                finish();
            }
        });

    }
}