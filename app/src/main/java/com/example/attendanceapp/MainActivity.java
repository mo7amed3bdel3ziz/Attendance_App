package com.example.attendanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.attendanceapp.Ui.CreateAcc;
import com.example.attendanceapp.Ui.HomeMain;
import com.example.attendanceapp.Ui.InFoActivity;
import com.example.attendanceapp.Ui.LoginActivity;
import com.example.attendanceapp.network.RetrofitRefranc;
import com.example.attendanceapp.pojo.authModel.LoginModel;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //  final WifiManager wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
     //  String macAddr = null;
     //  if (wm != null)
     //  macAddr = wm.getConnectionInfo().getMacAddress();

//        Intent i=new Intent(MainActivity.this, InFoActivity.class);
//        startActivity(i);
//        finish();
      String macAddress = android.provider.Settings.Secure.
                      getString(this.getApplicationContext().getContentResolver(), "android_id");
      Log.d("macAddress",macAddress);

        ImageView gifImageView = findViewById(R.id.imageView);
        Glide.with(this).asGif().load(R.drawable.login).into(gifImageView);

        // String androidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

     // UUID deviceUuid = new UUID(macAddress.hashCode(), androidId.hashCode());
     // String deviceId = deviceUuid.toString();
     // Toast.makeText(this,deviceId, Toast.LENGTH_SHORT).show();
      //  TelephonyManager tManager = (TelephonyManager)myActivity.getSystemService(Context.TELEPHONY_SERVICE);
      //  String uid = tManager.getDeviceId();
      //  String serialNumber;

      // try {
      //     Class<?> c = Class.forName("android.os.SystemProperties");
      //     Method get = c.getMethod("get", String.class, String.class);

      //     serialNumber = (String) get.invoke(c, "sys.serialnumber", "error");
      //     if (serialNumber.equals("error")) {
      //         serialNumber = (String) get.invoke(c, "ril.serialnumber", "error");
      //     }
      // } catch (Exception e) {
      //     e.printStackTrace();
      // }
     //   Toast.makeText(this, getMacAddr(), Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, macAddress, Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, macAddress, Toast.LENGTH_SHORT).show();
        Single s=    RetrofitRefranc.getInstance().getApiCalls().login(macAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        SingleObserver <LoginModel> observer=new SingleObserver<LoginModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onSuccess(@NonNull LoginModel loginModel) {
                if (loginModel.getState()==200){
                   // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();
                    //in app
                    //int s=    loginModel.getEmployeeID();
                  //  Toast.makeText(MainActivity.this, loginModel.getEmployeeID(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this, HomeMain.class);
                   i.putExtra("EmployeeID",loginModel.getEmployeeID());
                 //   i.putExtra("EmployeeID",s);
                    startActivity(i);
                    finish();
                }else if (loginModel.getState()==201){
                    //progras
                    Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                   // loginModel.getMessage();

                }else if(loginModel.getState()==202){
                    Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();
                    Intent i=new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }



            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        s.subscribe(observer);

   //   Thread background = new Thread() {
   //       public void run() {
   //           try {
   //               // Thread will sleep for 5 seconds
   //               sleep(3 * 1000);

   //               Intent i=new Intent(getBaseContext(), LoginActivity.class);
   //               startActivity(i);
   //               finish();

   //           } catch (Exception e) {
   //           }
   //       }
   //   };
   //   // start thread
   //   background.start();

    }
   public  static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    String hex = Integer.toHexString(b & 0xFF);
                    if (hex.length() == 1)
                        hex = "0".concat(hex);
                    res1.append(hex.concat(":"));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "";
    }
}