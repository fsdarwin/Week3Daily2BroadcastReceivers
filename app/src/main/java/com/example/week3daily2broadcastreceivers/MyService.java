package com.example.week3daily2broadcastreceivers;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MyService extends IntentService {
    Alblum alblum;
    Random random;
    Bundle bundle;
    ArrayList<Alblum> alblumArr;
    BroadcastReceiver myBroadcastReceiver;
    public static final String TAG = "FRANK: ";

    public MyService() {
        super("");
    }

    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        random = new Random();
        bundle = new Bundle();
        alblumArr = new ArrayList<>();

        Log.d(TAG, "onHandleIntent: JUST IN onHandleIntent");

        for (int i = 0; i < 10; i++){
            alblum = new Alblum();
            alblum.setSong(songArr[random.nextInt(10)]);
            alblum.setTitle(titleArr[random.nextInt(10)]);
            alblum.setYear(yearArr[random.nextInt(10)]);
            alblum.setImage(imageArr[random.nextInt(10)]);
            alblumArr.add(alblum);
            Log.d(TAG, "onHandleIntent: ALBLUM: " + alblum.getSong()+ " " + alblum.getTitle()+ " " + alblum.getYear());
        }
        bundle.putParcelableArrayList("alblumArr", alblumArr);
        // New Intent
        Intent bcIntent = new Intent();
        // Set action for implicit intent
        bcIntent.setAction("Zappa");
        bcIntent.putExtras(bundle);
        Log.d(TAG, "onCreate: SENDING BROADCAST");
        //Send broadcast locally (to application)
        sendBroadcast(bcIntent);
        stopSelf();
    }
    //Arrays for random selection of fields
    String[] songArr = {"Enchidna's Arf (of You)",
            "Fembot in a Wet T-Shirt",
            "Who Needs the Peace Corp?",
            "five Five FIVE",
            "Dinah Moe Hum",
            "Regyption Strut",
            "What's New in Baltimore?",
            "Apostrophe",
            "Black Napkins",
            "Ship Arriving to Late to Save a Drowning Witch"};

    String[] yearArr ={"1965",
            "1967",
            "1968",
            "1969",
            "1976",
            "1985",
            "1973",
            "1989",
            "1977",
            "1990"};

    String[] titleArr = {"We're Only in it for the Money",
            "Joe's Garage",
            "Weasels Ripped My Flesh",
            "Apostrophe",
            "Shut Up and Play Your Guitar",
            "Hot Rats",
            "Ship Arriving to Late to Save a Drowning Witch",
            "Zoot Allures",
            "Zappa Meets the Mothers of Prevention",
            "Absolutely Free"};

    String[] imageArr = {"https://www.progarchives.com/progressive_rock_discography_covers/1023/cover_21381662016_r.jpg",
            "https://i.scdn.co/image/4722a6945ba108375b040207aa10a5c96071119ehttps://vignette.wikia.nocookie.net/albumcovers/images/e/eb/Frank_zappa_-_We%27re_Only_In_It_For_The_Money.jpg/revision/latest?cb=20150225122612",
            "https://upload.wikimedia.org/wikipedia/en/9/9a/Zappa_Joe%27s_Garage.jpg",
            "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjByMOnyoLgAhVKJKwKHa76CuMQjRx6BAgBEAU&url=https%3A%2F%2Fgiphy.com%2Fgifs%2FuDiscoverMusic-album-cover-udiscover-ukfhvdmbAzE6vZG2KZ&psig=AOvVaw0OuKn03N0Q7RF5YSHzz_u9&ust=1548287231637961",
            "https://i.pinimg.com/236x/1d/8e/a3/1d8ea382efc5f7dbe931f5ef0aa0cea0--lp-covers-music-covers.jpg",
            "https://i.pinimg.com/236x/ca/50/f7/ca50f7cf2465fe84bd9b64b60b3376d1--frank-zappa-cd-album.jpg",
            "https://www.dustygroove.com/images/products/z/zappa_frank_wakajawak_101b.jpg",
            "https://www.udiscovermusic.com/wp-content/uploads/2017/11/Frank-Zappa-Zoot-Allures-Album-Cover-web-optimised-820.jpg",
            "http://www.progarchives.com/progressive_rock_discography_covers/1023/cover_550116122010.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/41ZYfzfZgAL.jpg"};
}
