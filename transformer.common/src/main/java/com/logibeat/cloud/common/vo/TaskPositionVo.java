package com.logibeat.cloud.common.vo;

/**
 * Created by wilson on 2017/8/23.
 */
public class TaskPositionVo {


    private Double origatLat;

    private Double origatLng;

    private String origatAddress;

    private Double destLat;

    private Double destLng;

    private String destAddress;

    private Double currentLat;

    private Double currentLng;

    private String currentAddress;


    public Double getOrigatLat() {
        return origatLat;
    }

    public void setOrigatLat(Double origatLat) {
        this.origatLat = origatLat;
    }

    public Double getOrigatLng() {
        return origatLng;
    }

    public void setOrigatLng(Double origatLng) {
        this.origatLng = origatLng;
    }

    public String getOrigatAddress() {
        return origatAddress;
    }

    public void setOrigatAddress(String origatAddress) {
        this.origatAddress = origatAddress;
    }

    public Double getDestLat() {
        return destLat;
    }

    public void setDestLat(Double destLat) {
        this.destLat = destLat;
    }

    public Double getDestLng() {
        return destLng;
    }

    public void setDestLng(Double destLng) {
        this.destLng = destLng;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public Double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(Double currentLat) {
        this.currentLat = currentLat;
    }

    public Double getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(Double currentLng) {
        this.currentLng = currentLng;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
}
