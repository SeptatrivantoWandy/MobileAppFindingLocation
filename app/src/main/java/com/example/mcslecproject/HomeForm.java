package com.example.mcslecproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

public class HomeForm extends AppCompatActivity {

    public static final String SEND_ID = "com.example.mcslecproject.SEND_ID";

    UserDataDBHelper UDdb;
    int userId;

    RecyclerView locationRV;

    public static Vector<LocationData> locations = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_form);

        UDdb = new UserDataDBHelper(HomeForm.this);

        MainActivity.UDV.clear();
        storeUDInVector();

        Intent intent = getIntent();
        userId = intent.getIntExtra(MainActivity.SEND_ID, -1);



        locations.clear();
        locationVectorAdd();



        locationRV = findViewById(R.id.locationRV);
        locationRV.setLayoutManager(new GridLayoutManager(this, 1));
        LocationAdapter adp = new LocationAdapter(userId, this);
        adp.setLocations(locations);
        locationRV.setAdapter(adp);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.menuPlaces);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Intent intent;

                switch (menuItem.getItemId()){

                    case R.id.menuPlaces:

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

                        intent = new Intent(getApplicationContext(), ProfileForm.class);

                        intent.putExtra(SEND_ID, userId);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();

                        return true;
                }

                return false;
            }
        });

    }

    public void locationVectorAdd(){
        locations.add(new LocationData(
                "0",
                R.drawable.taman_mini_indonesia_indah,
                "Taman Mini Indonesia Indah",
                "Taman Mini Indonesia Indah merupakan salah satu destinasi wisata yang sudah sangat di kenal oleh masyarakat Indonesia pada umumnya. Temat ini sudah ada dan di bangun pada era pemerintahan Presiden kedua Indonesia saat itu. Tempat yang ini banyak memiliki fasilitas yang bisa Anda jumpai dan nikmati nantinya.",
                "Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta",
                "7:00 AM – 10:00 PM",
                "(021) 8779 2078",
                "https://www.tamanmini.com/pesona_indonesia/",
                "106.89513443902813",
                "-6.302157967522159"));

        locations.add(new LocationData(
                "1",
                R.drawable.taman_safari_indonesia,
                "Taman Safari Indonesia",
                "Taman Safari Indonesia atau hanya Taman Safari adalah taman hiburan hewan di Cisarua, Prigen, dan Bali. Menjadi bagian dari organisasi yang sama, mereka dikenal sebagai Taman Safari I, II dan III. Yang paling populer adalah Taman Safari I.",
                "Jl. Kapten Harun Kabir No.724, Cibeureum, Kec. Cisarua, Bogor, Jawa Barat 16750",
                "8:30 AM – 5:00 PM",
                "1 500 212",
                "https://tamansafari.com/",
                "106.95142722553726",
                "-6.719656171965966"
        ));

        locations.add(new LocationData(
                "2",
                R.drawable.dunia_fantasi,
                "Dunia Fantasi",
                "Tempat hiburan yang terletak di kompleks Taman Impian Jaya Ancol (Ancol taman impian), Jakarta Utara, Indonesia. Dunia Fantasi mempunyai maskot berupa kera bekantan yang diberi nama Dufan (singkatan dari Dunia Fantasi). Dipilih kera sebagai karakter adalah untuk mengingatkan bahwa Ancol dahulu adalah kawasan kera. Pemilihan kera bekantan adalah semata-mata untuk mengenalkan jenis satwa langka yang kini dilindungi.",
                "Jl. Lodan Timur No.7, RW.10, Ancol, Kec. Pademangan, Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14430",
                "10:00 AM – 5:00 PM",
                "(021) 29222222",
                "https://www.ancol.com",
                "106.8335377",
                "-6.124288310966581"
        ));

        locations.add(new LocationData(
                "3",
                R.drawable.taman_nasional_ujung_kulon,
                "Taman Nasional Ujung Kulon",
                "Taman Nasional Ujung Kulon terletak di bagian paling barat Pulau Jawa, Indonesia. Kawasan taman nasional ini pada mulanya meliputi wilayah Krakatau dan beberapa pulau kecil di sekitarnya seperti Pulau Handeuleum dan Pulau Peucang dan Pulau Panaitan. Kawasan taman nasional ini mempunyai luas sekitar 122.956 Ha; (443 km² di antaranya adalah laut), yang dimulai dari Semenanjung Ujung Kulon sampai dengan Samudera Hindia.",
                "Jl. Perintis Kemerdekaan No.51 Kecamatan Labuan, Pandeglang, Banten 42264",
                "Buka 24 jam",
                "(0253) 801731",
                "https://www.ujungkulon.org",
                "105.3750766108944",
                "-6.7845558969381825"
        ));

        locations.add(new LocationData(
                "4",
                R.drawable.garuda_wisnu_kencana,
                "Garuda Wisnu Kencana",
                "sebuah taman wisata budaya di bagian selatan pulau Bali. Taman wisata ini terletak di Desa Ungasan, Kecamatan Kuta Selatan, Kabupaten Badung, kira-kira 40 kilometer di sebelah selatan Denpasar, ibu kota provinsi Bali.Di sini berdiri megah sebuah landmark atau maskot Bali, yakni patung Garuda Wisnu Kencana yang menggambarkan sosok Dewa Wisnu menunggangi tunggangannya, Garuda, setinggi 121 meter.",
                "Jl. Raya Uluwatu, Ungasan, Kuta Selatan Badung 80364, Bali-Indonesia",
                "10:00 AM – 10:00 PM",
                "+62 (361) 700 808",
                "https://www.gwkbali.com/",
                "115.167599",
                "-8.810423"
        ));

        locations.add(new LocationData(
                "5",
                R.drawable.taman_buah_mekarsari,
                "Taman Buah Mekarsari",
                "Mekarsari Taman Buah merupakan salah satu pusat pelestarian keanekaragaman hayati buah-buahan tropika terbesar di dunia, khususnya jenis buah-buahan unggul yang dikumpulkan dari seluruh daerah di Indonesia, sekaligus merupakan tempat penelitian budidaya (agronomi), pemuliaan (breeding) dan perbanyakan bibit unggul untuk kemudian disebarluaskan kepada petani dan masyarakat umum.",
                "Jalan Raya Cileungsi -Jonggol KM.3, Mekarsari, Cileungsi, Bogor, Jawa Barat, Indonesia, 16820.",
                "8:00 AM – 4:00 PM",
                "0812 8098 8009",
                "http://mekarsari.com/web/",
                "106.98465125213527",
                "-6.415137406003558"
        ));

        locations.add(new LocationData(
                "6",
                R.drawable.danau_kaolin_air_bara,
                "Danau Kaolin Air Bara",
                "danau ini terbentuk dari bekas tempat pertambangan Kaolin yang telah ditinggalkan oleh masyarakat setempat dan dengan sendirinya bekas galian tersebut dibentuk oleh “tangan-tangan” alam menjadi sebuah danau yang indah.",
                "Jl. Raya Gadung, Nibung, Koba, Kabupaten Bangka Tengah, Kepulauan Bangka Belitung 33782, Indonesia",
                "7:30 AM – 6:00 PM",
                "no data",
                "no data",
                "106.35281628132904",
                "-2.5475954241358734"
        ));

        locations.add(new LocationData(
                "7",
                R.drawable.pantai_tanjung_pesona,
                "Pantai Tanjung Pesona",
                "Pantai ini berada di tengah tempat antara Pantai Teluk Uber dan Pantai Tikus. Pantai ini mempunyai panorama laut lepas, di atas tanjung dengan susunan bebatuan besar nan eksotik. Pantai Tanjung Pesona telah dilengkapi berbagai fasilitas wisata, dengan klasifikasi hotel berbintang empat.",
                "Jalan Pantai Rebo, Sungailiat, 33211 Sungailiat, Bangka, Bangka Belitung.",
                "Buka 24 jam",
                "no data",
                "no data",
                "106.17178097944978",
                "-1.8759534919992533"
        ));
    }

    public int storeUDInVector () {
        Cursor cursor = UDdb.readAllUserData();

        if(cursor.getCount() == 0) {

            return -1;

        } else {

            while (cursor.moveToNext()) {

                UserData obj =  new UserData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                MainActivity.UDV.add(obj);
            }
            return 1;
        }
    }

}