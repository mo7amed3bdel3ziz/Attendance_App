package com.example.attendanceapp.pojo.authModel;

public class CreateACC {
  String AndroidID     ;
  String Employee_Name ;
  String Employee_Email;
  String Employee_Phone;
  String Manager_Name  ;
  String Employee_SSN  ;

    public CreateACC(String androidID, String employee_Name, String employee_Email,
                     String employee_Phone, String manager_Name, String employee_SSN) {
        AndroidID = androidID;
        Employee_Name = employee_Name;
        Employee_Email = employee_Email;
        Employee_Phone = employee_Phone;
        Manager_Name = manager_Name;
        Employee_SSN = employee_SSN;
    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getEmployee_Email() {
        return Employee_Email;
    }

    public void setEmployee_Email(String employee_Email) {
        Employee_Email = employee_Email;
    }

    public String getEmployee_Phone() {
        return Employee_Phone;
    }

    public void setEmployee_Phone(String employee_Phone) {
        Employee_Phone = employee_Phone;
    }

    public String getManager_Name() {
        return Manager_Name;
    }

    public void setManager_Name(String manager_Name) {
        Manager_Name = manager_Name;
    }

    public String getEmployee_SSN() {
        return Employee_SSN;
    }

    public void setEmployee_SSN(String employee_SSN) {
        Employee_SSN = employee_SSN;
    }
}
