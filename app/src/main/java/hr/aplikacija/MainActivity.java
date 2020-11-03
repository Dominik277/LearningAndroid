package hr.aplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView statusTextView;
    Button searchButton;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> bluetootDevices = new ArrayList<>();
    ArrayList<String> adresses = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          String action = intent.getAction();
          Log.i("Action",action);
          if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
              statusTextView.setText("Finished!");
              searchButton.setEnabled(true);
          }else if (BluetoothDevice.ACTION_FOUND.equals(action)){
              BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
              String name = device.getName();
              String adress = device.getAddress();
              String rssi = Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE));
              //Log.i("Device Found!","Name: " + name + " Adress: " + adress + " RSSI: " + rssi);
              if (!adresses.contains(adress)){
                  adresses.add(adress);
                  String deviceString = "";
                  if(name == null || name.equals("")){
                      deviceString = adress + " -RSSI " + rssi + "dBm";
                  }else {
                      deviceString = name + " -RSSI " + rssi + "dBm";
                  }
                  bluetootDevices.add(deviceString);
                  arrayAdapter.notifyDataSetChanged();
              }
          }
        }
    };

    public void searchClicked(View view){
        statusTextView.setText("Searching...");
        searchButton.setEnabled(false);
        bluetootDevices.clear();
        adresses.clear();
        bluetoothAdapter.startDiscovery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        statusTextView = findViewById(R.id.statusTextView);
        searchButton = findViewById(R.id.searchButton);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,bluetootDevices);
        listView.setAdapter(arrayAdapter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver,intentFilter);



    }
}