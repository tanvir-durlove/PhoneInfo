package com.example.td.phoneinfo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        textView4 = (TextView) findViewById(R.id.text4);


        //check permission
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }

        //get the google play service informations
        try {

            //Google play service version name
            String version = getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;

            //Google play service fisrt install time
            long install = getPackageManager().getPackageInfo("com.google.android.gms", 0).firstInstallTime;

            //Google play service Last Update time
            long lastUpdate = getPackageManager().getPackageInfo("com.google.android.gms", 0).lastUpdateTime;

            Date installtime = new Date(install);
            Date lastUpdatetime = new Date(lastUpdate);

            //Date format
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault());

            textView.setText(" " + version + "\n\n" + dateFormat.format(installtime) + "\n\n"
                    + dateFormat.format(lastUpdatetime));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //to get all hardware info
        Log.i("TAG", "SERIAL: " + Build.SERIAL);
        Log.i("TAG", "MODEL: " + Build.MODEL);
        Log.i("TAG", "ID: " + Build.ID);
        Log.i("TAG", "Manufacture: " + Build.MANUFACTURER);
        Log.i("TAG", "brand: " + Build.BRAND);
        Log.i("TAG", "type: " + Build.TYPE);
        Log.i("TAG", "user: " + Build.USER);
        Log.i("TAG", "BASE: " + Build.VERSION_CODES.BASE);
        Log.i("TAG", "INCREMENTAL " + Build.VERSION.INCREMENTAL);
        Log.i("TAG", "SDK  " + Build.VERSION.SDK);
        Log.i("TAG", "BOARD: " + Build.BOARD);
        Log.i("TAG", "BRAND " + Build.BRAND);
        Log.i("TAG", "HOST " + Build.HOST);
        Log.i("TAG", "FINGERPRINT: " + Build.FINGERPRINT);
        Log.i("TAG", "Version Code: " + Build.VERSION.RELEASE);

        String serial = Build.SERIAL;
        String model = Build.MODEL;
        String id = Build.ID;
        String manufacturer = Build.MANUFACTURER;
        String brand = Build.BRAND;
        String type = Build.TYPE;
        String user = Build.USER;
        String sdk = Build.VERSION.SDK;
        String version_code = Build.VERSION.RELEASE;

        textView4.setText("Serial:" + serial + "\nModel:" + model + "\nID:" + id + "\nManufacturer" + manufacturer
                + "\nBrand:" + brand + "\nType:" + type + "\nUser:" + user + "\nSDK:" + sdk
                + "\nVersion Code:" + version_code);


    }

    //Request for getting iemi
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceImei();
        }
    }

    //Imei method
    private void getDeviceImei() {

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String deviceid = mTelephonyManager.getDeviceId();
        String phone_number = mTelephonyManager.getLine1Number();
        String provider_number = mTelephonyManager.getNetworkOperatorName();

        textView2 = (TextView) findViewById(R.id.text2);
        textView2.setText("Device Imei Number : " +deviceid);

        textView3 = (TextView) findViewById(R.id.text3);
        textView3.setText(provider_number + " :" + phone_number);
    }
}
