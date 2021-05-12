package com.logibeat.cloud.dto;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

public class DriverGpsResDto extends EntitySerialize {

    private String id;

    private String outDestinationId;

    private Double lat;

    private Double lng;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutDestinationId() {
        return outDestinationId;
    }

    public void setOutDestinationId(String outDestinationId) {
        this.outDestinationId = outDestinationId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
