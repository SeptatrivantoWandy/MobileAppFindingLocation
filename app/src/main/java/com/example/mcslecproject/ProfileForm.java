package com.example.mcslecproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class ProfileForm extends AppCompatActivity {

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeForm.class);
        intent.putExtra(SEND_ID, userId);

        startActivity(intent);
        finish();

        super.onBackPressed();
    }


    UserData object_userData;

    TextView tvUserName;
    TextView tvEmail;
    TextView tvPhoneNumber;
    TextView tvUserDOB;
    TextView tvUserGender;

    String userName;
    String userEmail;
    String userPhoneNumber;
    String userDOB;
    String userGender;

    int userId;

    Button buttonLogOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);


        UserDataDBHelper UDdb = new UserDataDBHelper(ProfileForm.this);

        tvUserName = findViewById(R.id.textViewDisplauUserNameProf);
        tvEmail = findViewById(R.id.textViewDisplayEmailProf);
        tvPhoneNumber = findViewById(R.id.textViewDisplayPhoneNumberProf);
        tvUserDOB = findViewById(R.id.textViewDIsplayDOBProf);
        tvUserGender = findViewById(R.id.textViewDisplayGenderProf);

        buttonLogOut = findViewById(R.id.buttonLogOutProf);

        Intent intent = getIntent();
        userId = intent.getIntExtra(HomeForm.SEND_ID, -1);

        userName = MainActivity.UDV.get(userId).getUserName();
        userEmail = MainActivity.UDV.get(userId).getUserEmail();
        userPhoneNumber = MainActivity.UDV.get(userId).getUserPhoneNumber();
        userDOB = MainActivity.UDV.get(userId).getUserDOB();
        userGender = MainActivity.UDV.get(userId).getUserGender();

        tvUserName.setText(userName);
        tvEmail.setText(userEmail);
        tvPhoneNumber.setText(userPhoneNumber);
        tvUserDOB.setText(userDOB);
        tvUserGender.setText(userGender);



        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "account logged out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menuProfile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Intent intent;

                switch (menuItem.getItemId()){


                    case R.id.menuPlaces:

                        intent = new Intent(getApplicationContext(), HomeForm.class);
                        intent.putExtra(SEND_ID, userId);
//                        Toast.makeText(getApplicationContext(), String.valueOf(userId), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();

                        return true;


                    case R.id.menuBookmark:

                        intent = new Intent(getApplicationContext(), BookmarkForm.class);
                        intent.putExtra(SEND_ID, userId);
//                        Toast.makeText(getApplicationContext(), String.valueOf(userId), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();

                        return true;


                    case R.id.menuProfile:

                        return true;
                }

                return false;
            }
        });

    }
}