package com.example.attendanceapp.pojo;

public class ProfileModel {
    private String EmpId;
    private String EnEmpName;
    private String AEmpName;
    private String DateOfHiring;
    private String EndOfHiring;
    private String EmployeeInsrance;
    private String Countviolations;
    private String CountDeductions;
    private String CountAbsent;
    private String EmployeeSalary;

    public ProfileModel() {
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getEnEmpName() {
        return EnEmpName;
    }

    public void setEnEmpName(String enEmpName) {
        EnEmpName = enEmpName;
    }

    public String getAEmpName() {
        return AEmpName;
    }

    public void setAEmpName(String AEmpName) {
        this.AEmpName = AEmpName;
    }

    public String getDateOfHiring() {
        return DateOfHiring;
    }

    public void setDateOfHiring(String dateOfHiring) {
        DateOfHiring = dateOfHiring;
    }

    public String getEndOfHiring() {
        return EndOfHiring;
    }

    public void setEndOfHiring(String endOfHiring) {
        EndOfHiring = endOfHiring;
    }

    public String getEmployeeInsrance() {
        return EmployeeInsrance;
    }

    public void setEmployeeInsrance(String employeeInsrance) {
        EmployeeInsrance = employeeInsrance;
    }

    public String getCountviolations() {
        return Countviolations;
    }

    public void setCountviolations(String countviolations) {
        Countviolations = countviolations;
    }

    public String getCountDeductions() {
        return CountDeductions;
    }

    public void setCountDeductions(String countDeductions) {
        CountDeductions = countDeductions;
    }

    public String getCountAbsent() {
        return CountAbsent;
    }

    public void setCountAbsent(String countAbsent) {
        CountAbsent = countAbsent;
    }

    public String getEmployeeSalary() {
        return EmployeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        EmployeeSalary = employeeSalary;
    }

    public ProfileModel(String empId, String enEmpName, String AEmpName, String dateOfHiring, String endOfHiring, String employeeInsrance, String countviolations, String countDeductions, String countAbsent, String employeeSalary) {
        EmpId = empId;
        EnEmpName = enEmpName;
        this.AEmpName = AEmpName;
        DateOfHiring = dateOfHiring;
        EndOfHiring = endOfHiring;
        EmployeeInsrance = employeeInsrance;
        Countviolations = countviolations;
        CountDeductions = countDeductions;
        CountAbsent = countAbsent;
        EmployeeSalary = employeeSalary;
    }
}
