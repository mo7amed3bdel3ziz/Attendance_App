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
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.attendanceapp.MainActivity;
import com.example.attendanceapp.R;
import com.example.attendanceapp.databinding.ActivityCreateAccBinding;
import com.example.attendanceapp.network.RetrofitRefranc;
import com.example.attendanceapp.pojo.LocationModelApi;
import com.example.attendanceapp.pojo.authModel.LoginModel;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CreateAcc extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    Button btnGetLocation, btnGetLocation2;
    TextView showLocation, showLocation2;
    LocationManager locationManager;
    String latitude, longitude;


    FusedLocationProviderClient fusedLocationProviderClient;

    ActivityCreateAccBinding binding;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_acc);

        Intent i = getIntent();
        Id = i.getIntExtra("EmployeeID", 0);

        //  setContentView(R.layout.activity_create_acc);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        // showLocation = findViewById(R.id.showLocation);
        // showLocation2 = findViewById(R.id.showLocation2);
        // btnGetLocation = findViewById(R.id.btnGetLocation);
        // btnGetLocation2 = findViewById(R.id.btnGetLocation2);

        // binding.imageView4.setVisibility(View.GONE);

        Handler handler = new Handler();

        Glide.with(CreateAcc.this).asGif().load(R.drawable.notattend).into(binding.imageView4);

        binding.btnGetLocation.setVisibility(View.GONE);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        binding.progressBar.setVisibility(View.GONE);
        //  String macAddress =
        //          android.provider.Settings.Secure.
        //                  getString(this.getApplicationContext().getContentResolver(), "android_id");

        //  Toast.makeText(this,  macAddress, Toast.LENGTH_SHORT).show();

//       btnGetLocation.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               //   attendanc();
//
//
//           //   Log.d("testAtt","ssss1");
//           ////   attendanc();
//           //   LocationModelApi loc=new LocationModelApi(23.4,34.4,3);
//           //   Single ss=RetrofitRefranc.getInstance().getApiCalls().Location(loc).
//           //           subscribeOn(Schedulers.io())
//           //           .observeOn(AndroidSchedulers.mainThread());
//           //   SingleObserver<LoginModel> observers=new SingleObserver<LoginModel>() {
//           //       @Override
//           //       public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//           //       }
//
//           //       @Override
//           //       public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LoginModel loginModel) {
//           //           if (loginModel.getState()==400){
//           //               Log.d("testAtt","ssss2");
//
//           //               Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //               // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //               // loginModel.getMessage();
//           //               //in app
//           //               //  Intent i=new Intent(CreateAcc.this, HomeMain.class);
//           //               //  startActivity(i);
//           //               //  finish();
//           //           }else if (loginModel.getState()==402){
//           //               //progras
//           //               Log.d("testAtt","ssss3");
//           //               Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //               //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //               // loginModel.getMessage();
//
//           //           }else if(loginModel.getState()==403){
//           //               Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //               //Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //               // loginModel.getMessage();
//
//
//           //       }else if(loginModel.getState()==401){
//           //           Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //           //Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//           //           // loginModel.getMessage();
//
//           //       }
//           //           Log.d("testAtt",loginModel.getMessage()+loginModel.getState());
//
//
//           //       }
//
//           //       @Override
//           //       public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//           //           Toast.makeText(CreateAcc.this,e.getMessage(), Toast.LENGTH_SHORT).show();
//           //           Log.d("testAtt","ssss5");
//           //       }
//           //   };
//           //   ss.subscribe(observers);
//
//
//       //    }
//
//               //   Location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//               //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//              locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//              //  location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//              if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                  OnGPS();
//              } else {
//                  getLocation();
//                  checkSelfPermissions();
//              }
//          }
//       });


        binding.btnGetLocation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnGetLocation2.setImageResource(R.drawable.aftersubimtion);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        binding.btnGetLocation2.setImageResource(R.drawable.submitbtn);// Replace with the ID of your new image resource
                    }
                }, 200);
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //  location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    // getLocation();
                    binding.progressBar.setVisibility(View.VISIBLE);
                    showLocationCurrentLocation();
                    //   Toast.makeText(CreateAcc.this, "d", Toast.LENGTH_SHORT).show();

                    //getLocation();
                    //  checkSelfPermissions();
                }

                //   Location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

                // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                // //  location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                //     OnGPS();
                // } else {
                //   getLocation();
                // checkSelfPermissions();
                //   }
            }
        });


    }

    private void OnGPS() {
        Log.d("yousifGps", "دخلOnGPS  ");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(

                CreateAcc.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                CreateAcc.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("yousifGps", "دخلPermission  ");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    Log.d("TestGps", "Lat هنا1" + location.getLatitude());
                    location.getLatitude();
                    Toast.makeText(CreateAcc.this, location.getLatitude() + "", Toast.LENGTH_SHORT).show();

                }
            });
            //  Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //  if (locationGPS != null) {
            //      double lat = locationGPS.getLatitude();
            //      Log.d("yousifGps","1دخلelse  "+lat);

            //      double longi = locationGPS.getLongitude();
            //      latitude = String.valueOf(lat);
            //      longitude = String.valueOf(longi);
            //      showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            //  }
            // else {
            // Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            //   }
        }
    }

    public static String getMacAddr() {
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
                    // res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }

    public void checkSelfPermissions() {
        if (ActivityCompat.checkSelfPermission(CreateAcc.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // Toast.makeText(MainActivity.this, "y", Toast.LENGTH_SHORT).show();
            // showLocation();
            getLocationNew();
        } else {
            //Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
            //showLocation();
            ActivityCompat.requestPermissions(CreateAcc.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    @SuppressLint("MissingPermission")
    public void getLocationNew() {
        //   fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null);
        //  fusedLocationProviderClient.getCurrentLocation(CurrentLocationRequest, CancellationToken)
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {
                    Geocoder geocoder = new Geocoder(CreateAcc.this, Locale.getDefault());
                    try {

                        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        Toast.makeText(CreateAcc.this, addressList.get(0).getLatitude() + "", Toast.LENGTH_SHORT).show();
                        Log.d("TestGps", "lat" + addressList.get(0).getLatitude());
                        // Log.d("tpiLocat","log"+  addressList.get(0).getLongitude());

                        Double d = addressList.get(0).getLatitude();
                        addressList.get(0).getLongitude();


                        // addressList.get(0).getPremises();
                        // addressList.get(0).getCountryName();
                        // addressList.get(0).getAdminArea();
                        // addressList.get(0).getPhone();

                        //    showLocation2.setText("Your Location: " + "\n" + "Latitude: " +addressList.get(0).getLatitude() + "\n" + "Longitude: " +  addressList.get(0).getLongitude());

                        if (d != null) {
                            attendanc(addressList.get(0).getLongitude(), addressList.get(0).getLatitude());
                        }

                        attendanc(addressList.get(0).getLongitude(), addressList.get(0).getLatitude());
                        Toast.makeText(CreateAcc.this, addressList.get(0).getPhone() + "aaaaaa", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        Toast.makeText(CreateAcc.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(CreateAcc.this, "aaaaaa", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void attendanc(Double Longitude, Double Latitude) {

        //  LocationModelApi loc=new LocationModelApi(23.4,34.4,2);
        // Single ss=RetrofitRefranc.getInstance().getApiCalls().Location(loc).
        //         subscribeOn(Schedulers.io())
        //          .observeOn(AndroidSchedulers.mainThread());
        //  SingleObserver<LoginModel> observers=new SingleObserver<LoginModel>() {
        //      @Override
        //      public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

        //      }

        //      @Override
        //      public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LoginModel loginModel) {
        //          if (loginModel.getState()==200){

        //       Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
        //              // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
        //              // loginModel.getMessage();
        //              //in app
        //            //  Intent i=new Intent(CreateAcc.this, HomeMain.class);
        //            //  startActivity(i);
        //            //  finish();
        //          }else if (loginModel.getState()==201){
        //              //progras
        // Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
        //           //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
        //              // loginModel.getMessage();

        //          }else if(loginModel.getState()==202){
        //              Toast.makeText(CreateAcc.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
        //              //Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
        //              // loginModel.getMessage();

        //          }


        //      }

        //      @Override
        //      public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
        //          Toast.makeText(CreateAcc.this,e.getMessage(), Toast.LENGTH_SHORT).show();

        //      }
        //  };
        //  ss.subscribe(observers);

        Log.d("testAtt", "ssss1");
        //   attendanc();
        LocationModelApi loc = new LocationModelApi(Longitude, Latitude, Id);
        Single ss = RetrofitRefranc.getInstance().getApiCalls().Location(loc).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<LoginModel> observers = new SingleObserver<LoginModel>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                //  Toast.makeText(CreateAcc.this, d.toString()+"ds", Toast.LENGTH_SHORT).show();
                // Log.d("testAtt","ssss2");
            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull LoginModel loginModel) {
                binding.progressBar.setVisibility(View.GONE);
                if (loginModel.getState() == 200) {
                    binding.progressBar.setVisibility(View.GONE);
                    Log.d("testAtt", "ssss2");
                    //binding.imageView4.setVisibility(View.VISIBLE);
                    binding.btnGetLocation2.setVisibility(View.GONE);
                    Glide.with(CreateAcc.this).asGif().load(R.drawable.ic_attendance).into(binding.imageView4);
                    binding.showLocation.setText(loginModel.getMessage() + " Attend");
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "200 فى اللوكيشن", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();
                    //in app
                    //  Intent i=new Intent(CreateAcc.this, HomeMain.class);
                    //  startActivity(i);
                    //  finish();

                } else if (loginModel.getState() == 201) {
                    //progras
                    Log.d("testAtt", "ssss3");
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "سجلت قبل كدا", Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "سجلت قبل كدا", Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "سجلت قبل كدا", Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();

                } else if (loginModel.getState() == 400) {
                    //progras
                    Log.d("testAtt", "ssss3");
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "400برا اللوكيشن ", Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();

                } else if (loginModel.getState() == 401) {
                    //progras
                    Log.d("testAtt", "ssss3");
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + " 401", Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();

                } else if (loginModel.getState() == 402) {
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "402", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();


                } else if (loginModel.getState() == 403) {
                    Toast.makeText(CreateAcc.this, loginModel.getMessage() + "403", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();

                }
                Log.d("testAtt", loginModel.getMessage() + loginModel.getState() + "404");


            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Toast.makeText(CreateAcc.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("testAtt", "ssss5");
                binding.progressBar.setVisibility(View.GONE);
            }
        };
        ss.subscribe(observers);

    }

    @SuppressLint("MissingPermission")
    public void showLocationCurrentLocation() {
        //   fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null).addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                //   binding.   showLocation2.setText("FFYourgetCurrentLocation Location: " + "\n" + "Latitude: " +location.getLatitude() + "\n" + "Longitude: " + location.getLongitude());

                attendanc(location.getLongitude(), location.getLatitude());
            }
        });
    }
}