package com.example.mcslecproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

public class BookmarkForm extends AppCompatActivity {

    RecyclerView bookmarkRV;

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeForm.class);
        intent.putExtra(SEND_ID, userId);

        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    int userId;
    TextView emptyTrData;

    UserDataDBHelper UDdb;
    BookmarkDBHelper BDdb;

    public static Vector<BookmarkData> BDVi = new Vector<>();
    public static Vector<BookmarkData> BDV = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_form);

        UDdb = new UserDataDBHelper(BookmarkForm.this);
        BDdb = new BookmarkDBHelper(BookmarkForm.this);

        MainActivity.UDV.clear();
        storeUDInVector();

        Intent intent = getIntent();
        userId = intent.getIntExtra(MainActivity.SEND_ID, -1);
        emptyTrData = findViewById(R.id.textViewEmptyData);

        if(userId == -1){
            Intent intentC = getIntent();
            userId = intentC.getIntExtra(ProfileForm.SEND_ID, -1);
            emptyTrData = findViewById(R.id.textViewEmptyData);
        }

        BDV.clear();
        storeBookmarkDataInVector();

        BDVi.clear();
        for(int i = 0 ; i < BDV.size() ; i++){
            if(BDV.get(i).getBookmarkUserId().contentEquals(userId+1+"")){
                BDVi.add(new BookmarkData(BDV.get(i).getBookmarkId(), Integer.parseInt(BDV.get(i).getBookmarkUserId())-1+"", Integer.parseInt(BDV.get(i).getBookmarkAttractionId())+""));

            }
        }

        if(BDVi.size() == 0){
            emptyTrData.setText("there is no bookmarked location");
        } else{
            bookmarkRV = findViewById(R.id.BookmarkRV);
            BookmarkAdapter bmadp = new BookmarkAdapter(this, userId);
            bmadp.setBookmarks(BDVi);

            bookmarkRV.setAdapter(bmadp);
            bookmarkRV.setLayoutManager(new GridLayoutManager(this, 1));

        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menuBookmark);

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

                        return true;


                    case R.id.menuProfile:

                        intent = new Intent(getApplicationContext(), ProfileForm.class);
                        intent.putExtra(SEND_ID, userId);
//                        Toast.makeText(getApplicationContext(), String.valueOf(userId), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();

                        return true;
                }

                return false;
            }
        });

    }

    public int storeUDInVector () {
        Cursor cursor = UDdb.readAllUserData();

        if(cursor.getCount() == 0) {

//            password.setError("either username or password cannot be found or false");
//            password.requestFocus();
            return -1;

        } else {

            while (cursor.moveToNext()) {

                UserData obj =  new UserData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                MainActivity.UDV.add(obj);
            }
            return 1;
        }
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