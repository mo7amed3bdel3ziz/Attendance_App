package com.example.attendanceapp.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendanceapp.R;
import com.example.attendanceapp.databinding.ActivityLeavingBinding;
import com.example.attendanceapp.network.RetrofitRefranc;
import com.example.attendanceapp.pojo.LocationModelApi;
import com.example.attendanceapp.pojo.authModel.LoginModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LeavingActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    Button btnGetLocation, btnGetLocation2;
    TextView showLocation,showLocation2;
    LocationManager locationManager;
    String latitude, longitude;


    FusedLocationProviderClient fusedLocationProviderClient;
    int Id;
    ActivityLeavingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_leaving);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_leaving);
        binding.progressBar2.setVisibility(View.GONE);
        binding.imageView5.setVisibility(View.GONE);
        Intent i=getIntent();
        Id=i.getIntExtra("EmployeeID",0);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        binding.button.setOnClickListener(view -> {
            binding.button.setImageResource(R.drawable.afterleave);
            Handler handler = new Handler();
            handler.postDelayed(() -> {

                binding.button.setImageResource(R.drawable.leavebtn);// Replace with the ID of your new image resource
            }, 200);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //  location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                OnGPS();
            } else {
                // getLocation();
               binding.progressBar2.setVisibility(View.VISIBLE);
                showLocationCurrentLocation();
                //getLocation();
                //  checkSelfPermissions();
            }
        });


    }
    public void attendanc( Double Longitude, Double Latitude){

                Log.d("testAtt","ssss1");
        //   attendanc();
        LocationModelApi loc=new LocationModelApi(Longitude,Latitude, Id);
        Single ss= RetrofitRefranc.getInstance().getApiCalls().leveingTime(loc).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<LoginModel> observers=new SingleObserver<LoginModel>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                //  Toast.makeText(CreateAcc.this, d.toString()+"ds", Toast.LENGTH_SHORT).show();
                // Log.d("testAtt","ssss2");
            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LoginModel loginModel) {
              //  binding.progressBar.setVisibility(View.GONE);
                binding.progressBar2.setVisibility(View.GONE);
                if (loginModel.getState()==1){
                  //  binding.button.setVisibility(View.GONE);
                 //   binding.progressBar.setVisibility(View.GONE);
                    Log.d("testAtt","ssss2");
                    binding.textView4.setText(loginModel.getMessage() +" Leaving");
                    //   binding.imageView4.setVisibility(View.VISIBLE)
                    binding.imageView5.setVisibility(View.VISIBLE);
                }
                else if (loginModel.getState()==6){
                    //progras
                    Log.d("testAtt","ssss3");
                    //binding.button.setVisibility(View.GONE);
                    binding.textView4.setText(loginModel.getMessage());
                  //  Toast.makeText(CreateAcc.this,loginModel.getMessage()+"سجلت قبل كدا", Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();

                }
                Log.d("testAtt",loginModel.getMessage()+loginModel.getState()+"404");
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
             //   Toast.makeText(CreateAcc.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("testAtt","ssss5");
                binding.progressBar2.setVisibility(View.GONE);
            //    binding.progressBar.setVisibility(View.GONE);
            }
        };
        ss.subscribe(observers);

    }
    @SuppressLint("MissingPermission")
    public void getLocationNew(){
        //   fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null);
        //  fusedLocationProviderClient.getCurrentLocation(CurrentLocationRequest, CancellationToken)
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();

                if(location!=null){
                    Geocoder geocoder=new Geocoder(LeavingActivity.this, Locale.getDefault());
                    try {

                        List<Address> addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        Toast.makeText(LeavingActivity.this,  addressList.get(0).getLatitude()+"", Toast.LENGTH_SHORT).show();
                        Log.d("TestGps","lat"+ addressList.get(0).getLatitude());
                        // Log.d("tpiLocat","log"+  addressList.get(0).getLongitude());

                        Double d=addressList.get(0).getLatitude();
                        addressList.get(0).getLongitude();


                        // addressList.get(0).getPremises();
                        // addressList.get(0).getCountryName();
                        // addressList.get(0).getAdminArea();
                        // addressList.get(0).getPhone();

                        showLocation2.setText("Your Location: " + "\n" + "Latitude: " +addressList.get(0).getLatitude() + "\n" + "Longitude: " +  addressList.get(0).getLongitude());

                        if (d!=null){
                            attendanc( addressList.get(0).getLongitude(), addressList.get(0).getLatitude());
                        }

                        attendanc( addressList.get(0).getLongitude(), addressList.get(0).getLatitude());
                        Toast.makeText(LeavingActivity.this,     addressList.get(0).getPhone()+"aaaaaa", Toast.LENGTH_SHORT).show();

                    }catch (IOException e){
                        Toast.makeText(LeavingActivity.this,  e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {

                    Toast.makeText(LeavingActivity.this, "aaaaaa", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    @SuppressLint("MissingPermission")
    public void showLocationCurrentLocation(){
        //   fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null).addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
              //  binding.   showLocation2.setText("FFYourgetCurrentLocation Location: " + "\n" + "Latitude: " +location.getLatitude() + "\n" + "Longitude: " + location.getLongitude());

                attendanc(location.getLongitude(),location.getLatitude());
            }
        });
    }
    private void OnGPS() {
        Log.d("yousifGps","دخلOnGPS  ");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}