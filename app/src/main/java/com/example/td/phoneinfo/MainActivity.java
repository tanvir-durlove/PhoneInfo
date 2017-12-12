package com.example.td.phoneinfo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private Context context;
    private TextView textView2;
    private TelephonyManager tm;

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        textView2 = (TextView) findViewById(R.id.text);
        try {
            String version = getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;
            long install = getPackageManager().getPackageInfo("com.google.android.gms", 0).firstInstallTime;
            long lastUpdate = getPackageManager().getPackageInfo("com.google.android.gms", 0).lastUpdateTime;

            Date installtime = new Date(install);
            Date lastUpdatetime = new Date(lastUpdate);

            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

            textView.setText(" " + version + "\n\n" + dateFormat.format(installtime) + "\n\n"
                    + dateFormat.format(lastUpdatetime));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
