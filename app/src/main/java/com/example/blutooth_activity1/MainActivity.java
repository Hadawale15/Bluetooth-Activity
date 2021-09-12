package com.example.blutooth_activity1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
   private Set<BluetoothDevice> pairdevice;


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list_view);
        bluetoothAdapter =BluetoothAdapter.getDefaultAdapter();
    }

    public void ON(View view) {
        if (!bluetoothAdapter.isEnabled()){
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,1);
            Toast.makeText(MainActivity.this, "Turned On", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(MainActivity.this, "already On", Toast.LENGTH_SHORT).show();
        }
    }

    public void OFF(View view) {
        bluetoothAdapter.disable();
        Toast.makeText(MainActivity.this, "Turned Off", Toast.LENGTH_SHORT).show();
    }

    public void VISIBLE(View view) {
        Intent intent=new Intent(bluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent,2);
    }

    public void SHOW(View view) {
        pairdevice=bluetoothAdapter.getBondedDevices();
        ArrayList arrayList=new ArrayList();
        for (BluetoothDevice device:pairdevice){
            arrayList.add(device.getName());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }
}