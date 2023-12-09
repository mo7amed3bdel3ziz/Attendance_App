package com.example.attendanceapp.network;

import androidx.room.AutoMigration;

import com.example.attendanceapp.pojo.AllClientsModel;
import com.example.attendanceapp.pojo.InFoModel;
import com.example.attendanceapp.pojo.LocationModelApi;
import com.example.attendanceapp.pojo.authModel.CreateACC;
import com.example.attendanceapp.pojo.authModel.LoginModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApisCalls {

     @Headers("Content-Type: application/json")
    @POST("api/Employee/Employee_Login")
    Single<LoginModel>login(@Body String s);


    @Headers("Content-Type: application/json")
    @POST("api/Employee/Employee_Registration_Request")
    Single<LoginModel>creatAcc(@Body CreateACC Map);

    @Headers("Content-Type: application/json")
    @POST("api/Employee/EmployeeAttendanceV2")
    Single<LoginModel>Location(
            @Body LocationModelApi Map);


    @Headers("Content-Type: application/json")
    @POST("api/Employee/EmployeeleavingTime")
    Single<LoginModel>leveingTime(
            @Body LocationModelApi Map);


    @Headers("Content-Type: application/json")
     @POST("api/Employee/GetEmployeeInfo")
       Single<InFoModel>getInfo(
               @Body int x);

    @Headers("Content-Type: application/json")
    @GET("api/Employee/GetAttendanceState")
    Single<LoginModel>testAtt(
            @Query("EmployeeId") int x);
}
