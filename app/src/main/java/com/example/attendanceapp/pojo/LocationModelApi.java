package com.example.attendanceapp.pojo;

public class LocationModelApi {

  Double Longitude;
  Double Latitude;
  int ID;

    public LocationModelApi(Double longitude, Double latitude, int ID) {
        Longitude = longitude;
        Latitude = latitude;
        this.ID = ID;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
