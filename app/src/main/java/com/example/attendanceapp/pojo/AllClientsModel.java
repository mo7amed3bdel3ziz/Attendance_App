package com.example.attendanceapp.pojo;

public class AllClientsModel {


    private String ClientName;
    private String ClientPhone;
    private Double TotalAmount;
    private Double totals;
    private Double Credit;
    private Double Depit;
    private String newName;
    private String OldPhone;

    public String getOldPhone() {
        return OldPhone;
    }

    public void setOldPhone(String oldPhone) {
        OldPhone = oldPhone;
    }
    //if(newPhone==""){
    //name=nameRoom=ClientPhone
    // phone= ClientPhone
    // }
    // else{
    //phone=newPhone
    //if()
    //neme =old name
    // }

    private String NewPhone;


    public String getNewPhone() {
        return NewPhone;
    }

    public void setNewPhone(String newPhone) {
        NewPhone = newPhone;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Double getCredit() {
        return Credit;
    }

    public void setCredit(Double credit) {
        Credit = credit;
    }

    public Double getDepit() {
        return Depit;
    }

    public void setDepit(Double depit) {
        Depit = depit;
    }

    public Double getTotals() {
        return totals;
    }

    public void setTotals(Double totals) {
        this.totals = totals;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }
}
