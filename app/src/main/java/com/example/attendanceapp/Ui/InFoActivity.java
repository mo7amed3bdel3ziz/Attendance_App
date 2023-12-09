package com.example.attendanceapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.widget.Toast;

import com.example.attendanceapp.R;

import com.example.attendanceapp.databinding.ActivityInFoBinding;
import com.example.attendanceapp.network.RetrofitRefranc;
import com.example.attendanceapp.pojo.InFoModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InFoActivity extends AppCompatActivity {
ActivityInFoBinding binding;
public static final int x=2;
int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_fo);

       binding = DataBindingUtil.setContentView(InFoActivity.this, R.layout.activity_in_fo);
        Intent i=getIntent();
        Id=i.getIntExtra("EmployeeID",0);
        Toast.makeText(InFoActivity.this, Id+"", Toast.LENGTH_SHORT).show();

        Single create=    RetrofitRefranc.getInstance().getApiCalls().getInfo(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver <InFoModel> observer=new SingleObserver<InFoModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
              //  Toast.makeText(InFoActivity.this,d.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(@NonNull InFoModel inFoModel) {

                binding.nameTextView.setText(inFoModel.getEmp_Info().getEnEmpName());
                binding.idTextView.setText(inFoModel.getEmp_Info().getEmpId());
                binding.phoneTextView.setText(inFoModel.getEmp_Info().getAEmpName());
                binding.emailTextView.setText(inFoModel.getEmp_Info().getCountAbsent());
               // binding.companyHeader.setText(inFoModel.getEmp_Info().getEmployeeCompany());
                binding.employeeHireDateTextView.setText(inFoModel.getEmp_Info().getDateOfHiring());
                binding.employeeYearExpTextView.setText(inFoModel.getEmp_Info().getEndOfHiring());
                binding.employeeSalaryTextView.setText(inFoModel.getEmp_Info().getEmployeeSalary());
                binding.employeeAddressTextView.setText(inFoModel.getEmp_Info().getCountviolations());
                Toast.makeText(InFoActivity.this, inFoModel.getEmp_Info().getEmpId()+"s", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(@NonNull Throwable e) {
              //  Toast.makeText(InFoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };

        create.subscribe(observer);
    }
}