package com.example.attendanceapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendanceapp.MainActivity;
import com.example.attendanceapp.R;

import com.example.attendanceapp.databinding.ActivityLoginBinding;
import com.example.attendanceapp.network.RetrofitRefranc;
import com.example.attendanceapp.pojo.authModel.CreateACC;
import com.example.attendanceapp.pojo.authModel.LoginModel;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
Button confirm;
EditText nationalID,nameManagerCreateAcc,nameCreateAcc,emailAddress,phoneCreateAcc;

ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_login);
       // confirm=findViewById(R.id.confirm);

       // nationalID          =findViewById(R.id.nationalID);
       // nameManagerCreateAcc=findViewById(R.id.nameManagerCreateAcc);
       // nameCreateAcc       =findViewById(R.id.nameCreateAcc);
       // emailAddress         =findViewById(R.id.emailAddress);
       // phoneCreateAcc      =findViewById(R.id.phoneCreateAcc);

       binding=  DataBindingUtil.setContentView(this, R.layout.activity_login);

       binding.progressBar4.setVisibility(View.GONE);
//
        binding.   confirm.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // Intent i=new Intent(LoginActivity.this, HomeMain.class);
             // startActivity(i);
              if ( isValed()==true){
                  binding.progressBar4.setVisibility(View.VISIBLE);

                  createAcc();
              }

          }
      });
    }
    public void createAcc(){
   String   nationalIDs           =  binding.  nationalID           .getText().toString();
   String   nameManagerCreateAccs =  binding.  nameManagerCreateAcc .getText().toString();
   String   nameCreateAccs         = binding.  nameCreateAcc        .getText().toString();
   String   emailAddresss          = binding.  emailAddress         .getText().toString();
   String   phoneCreateAccs       =  binding.  phoneCreateAcc       .getText().toString();




        String macAddress =
                android.provider.Settings.Secure.
                        getString(this.getApplicationContext().getContentResolver(), "android_id");


    //    Toast.makeText(LoginActivity.this, macAddress, Toast.LENGTH_SHORT).show();


        HashMap<String,String> Login=new HashMap<>();
         Login.put("AndroidID",      macAddress  );
         Login.put("Employee_Name",  nameCreateAccs ) ;
         Login.put("Employee_Email", emailAddresss ) ;
         Login.put("Employee_Phone", phoneCreateAccs )  ;
         Login.put("Manager_Name ",  nameManagerCreateAccs ) ;
         Login.put("Employee_SSN",    nationalIDs  ) ;


        Log.d("testAtt",macAddress);
        Log.d("testAtt",nameCreateAccs);
        Log.d("testAtt",emailAddresss);
        Log.d("testAtt",phoneCreateAccs);
        Log.d("testAtt",nameManagerCreateAccs);
        Log.d("testAtt",nationalIDs);


        CreateACC acc=new CreateACC(macAddress,nameCreateAccs,emailAddresss,phoneCreateAccs,nameManagerCreateAccs,nationalIDs);

//        Gson gson = new Gson();
//        gson.toJson(acc);
//        Log.d("CreateACC", gson.toJson(acc));


        Single create=    RetrofitRefranc.getInstance().getApiCalls().creatAcc(acc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        SingleObserver<LoginModel> observerCreate=new SingleObserver<LoginModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull LoginModel loginModel) {
                binding.progressBar4.setVisibility(View.GONE);
                Log.d("testAtt",loginModel.getMessage());

                if (loginModel.getState()==200){
                    Toast.makeText(LoginActivity.this,loginModel.getMessage()+" ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this,loginModel.getMessage()+" ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this,loginModel.getMessage()+" ", Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();
                    //in app
                    Intent i =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();


                }else if (loginModel.getState()==400){
                    //progras
                    Toast.makeText(LoginActivity.this,loginModel.getMessage()+"ssqss", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this,loginModel.getMessage()+"sqss", Toast.LENGTH_SHORT).show();
                    // loginModel.getMessage();


                }



            }

            @Override
            public void onError(@NonNull Throwable e) {
                binding.progressBar4.setVisibility(View.GONE);
                Log.d("testAtt",e.getMessage());

                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        create.subscribe(observerCreate);

    }
  // nationalID
  //         nameManagerCreateAcc
  // nameCreateAcc
  //         emailAddress
  // phoneCreateAcc
  public Boolean   isValed(){


      if (   binding. nameCreateAcc.getText().toString().trim().isEmpty()) {
          binding. nameCreateAcc.setError("Enter shoesName");

          return false;
      }
        if (   binding. nationalID.getText().toString().trim().isEmpty()) {
            binding. nationalID.setError("Enter shoesName");

            return false;
        }

        if  (  binding.  nameManagerCreateAcc.getText().toString().trim().isEmpty()) {
            binding.  nameManagerCreateAcc.setError("Enter shoesdescription");
            return false;
        }
        if  (    binding.  emailAddress.getText().toString().trim().isEmpty()) {
            binding.  emailAddress.setError("Enter shoesquantity");
            return false;
        }
        if  (   binding. phoneCreateAcc.getText().toString().trim().isEmpty()) {
            binding. phoneCreateAcc.setError("Enter sizes");
            return false;
        }
      {
          return true;

      }
    }
}