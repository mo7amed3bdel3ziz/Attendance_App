package com.example.attendanceapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.attendanceapp.MainActivity;
import com.example.attendanceapp.R;
import com.example.attendanceapp.network.RetrofitRefranc;
import com.example.attendanceapp.pojo.AllClientsModel;
import com.example.attendanceapp.pojo.authModel.LoginModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeMain extends AppCompatActivity {
CardView cardView,cardViewinfo;
ImageView  attendImageView,HrImageView,vavationsImageView;
    ProgressBar progressBar3;
public final static int s=2324;
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        cardView = findViewById(R.id.cardView);
        cardViewinfo = findViewById(R.id.cardViewinfo);
        progressBar3= findViewById(R.id.progressBar3);
        attendImageView= findViewById(R.id.imageView2);
        HrImageView= findViewById(R.id.imageView32);
        vavationsImageView= findViewById(R.id.imageView33);

        Intent i=getIntent();
        Id=i.getIntExtra("EmployeeID",0);
        Handler handler = new Handler();
        progressBar3.setVisibility(View.GONE);
        Toast.makeText(this, " id"+Id, Toast.LENGTH_SHORT).show();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar3.setVisibility(View.VISIBLE);
                attendImageView.setImageResource(R.drawable.afterattend);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        attendImageView.setImageResource(R.drawable.attendance);// Replace with the ID of your new image resource
                    }
                }, 200);
                Single s=    RetrofitRefranc.getInstance().getApiCalls().testAtt(Id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

                SingleObserver<LoginModel> observer=new SingleObserver<LoginModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull LoginModel loginModel) {
                        progressBar3.setVisibility(View.GONE);

                        if (loginModel.getState()==1){

                            Intent i = new Intent(HomeMain.this, CreateAcc.class);
                            i.putExtra("EmployeeID",  Id);
                            startActivity(i);

                            // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                            // Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                            // loginModel.getMessage();
                            //in app\
                            //    int s=    loginModel.getEmployeeID();
                            //  Toast.makeText(MainActivity.this, loginModel.getEmployeeID(), Toast.LENGTH_SHORT).show();
                         //   Intent i=new Intent(MainActivity.this, HomeMain.class);
                         //   i.putExtra("EmployeeID",loginModel.getEmployeeID());
                         //   //   i.putExtra("EmployeeID",s);
                         //   startActivity(i);
                         //   finish();
                        }else if (loginModel.getState()==2){
                            Intent i = new Intent(HomeMain.this, LeavingActivity.class);
                            i.putExtra("EmployeeID",  Id);
                            startActivity(i);
                            //progras
                         //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                         //   Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                            // loginModel.getMessage();

                        }
                      //  else if(loginModel.getState()==202){
                      //      Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                      //      Toast.makeText(MainActivity.this,loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                      //      // loginModel.getMessage();
                      //      Intent i=new Intent(getBaseContext(), LoginActivity.class);
                      //      startActivity(i);
                      //      finish();
                      //  }



                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressBar3.setVisibility(View.GONE);

                        Toast.makeText(HomeMain.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                };
                s.subscribe(observer);
               // Intent i = new Intent(HomeMain.this, CreateAcc.class);
               // i.putExtra("EmployeeID",  Id);
               // startActivity(i);
            }
        });

        cardViewinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HrImageView.setImageResource(R.drawable.afterhr);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HrImageView.setImageResource(R.drawable.hr);// Replace with the ID of your new image resource
                    }
                }, 200);
                Intent i = new Intent(HomeMain.this, InFoActivity.class);
                i.putExtra("EmployeeID",  Id);
                startActivity(i);
            }
        });
    }
}