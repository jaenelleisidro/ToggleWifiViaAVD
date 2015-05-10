package togglewifiviaadb.securitywizards.herobo.com.togglewifiviaadb;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by iii on 5/10/2015.
 */
public class WifiService extends Service {
    private final IBinder mBinder = new MyBinder();
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Random random = new Random();
        if (random.nextBoolean()) {
            list.add("Linux");
        }
        if (random.nextBoolean()) {
            list.add("Android");
        }
        if (random.nextBoolean()) {
            list.add("iPhone");
        }
        if (random.nextBoolean()) {
            list.add("Windows7");
        }
        if (list.size() >= 20) {
            list.remove(0);
        }

        Bundle extras = intent.getExtras();
        if(extras.containsKey("iswifienabled")){

        }
        if(intent.getBooleanExtra("iswifienabled",true)) {
            Toast.makeText(getApplicationContext(), "wifi enabled", Toast.LENGTH_LONG).show();
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);
        }else{
            Toast.makeText(getApplicationContext(), "wifi disabled", Toast.LENGTH_LONG).show();
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
        }


        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        WifiService getService() {
            return WifiService.this;
        }
    }

    public List<String> getWordList() {
        return list;
    }
}
