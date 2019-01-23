package com.example.week3daily2broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FRANK: ";
    RecyclerViewAdapter rvAdapter;
    RecyclerView recyclerView;
    BroadcastReceiver broadcastReceiver;
    ArrayList<Alblum> alblumArr;
    ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set up RecyclerView
        recyclerView = findViewById(R.id.rvMainRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Set up the Intent and call the Service
        Intent intent = new Intent(this, MyService.class);
        Log.d(TAG, "onCreate: STARTING SERVICE");
        startService(intent);
        //Set up ontent filter for BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Zappa");
        //Instantiate the BroadcastReceiver
        broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: RECEIVING BROADCAST");
                //When broadcast received, pull the array out of the bundle
                alblumArr = intent.getExtras().getParcelableArrayList("alblumArr");
                Log.d("TAG", "onReceive: ");
                //Finish setting up the RV Adapter
                rvAdapter = new RecyclerViewAdapter(alblumArr);
                recyclerView.setAdapter(rvAdapter);
            }
        }; // Register the Broadcast Reciever
        registerReceiver(broadcastReceiver, intentFilter);


        serviceConnection = new ServiceConnection() {
            boolean isBound = false;
            Messenger mMessenger;
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                isBound = true;

                // Create the Messenger object
                mMessenger = new Messenger(service);

                // Create a Message
                // Note the usage of MSG_SAY_HELLO as the what value
                Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);

                // Create a bundle with the data
                Bundle bundle = new Bundle();
                bundle.putString("Zappa", "The crux of the biscuit is the apostrophe.");

                // Set the bundle data to the Message
                msg.setData(bundle);

                // Send the Message to the Service (in another process)
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // unbind or process might have crashes
                mMessenger = null;
                isBound = false;
            }
        };
        Intent MsIntent = new Intent(this, MessengerService.class);
        bindService(MsIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}








