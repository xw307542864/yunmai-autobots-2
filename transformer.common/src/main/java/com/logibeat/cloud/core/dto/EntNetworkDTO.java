package com.logibeat.cloud.core.dto;

import com.logibeat.cloud.util.tools.pageMdl.Page;

public class EntNetworkDTO extends Page {

    private Double lat;
    private Double lng;
    private String entId;
    private String networkNameLike;
    private Integer maxDistance;
    private String baseUserId;

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

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getNetworkNameLike() {
        return networkNameLike;
    }

    public void setNetworkNameLike(String networkNameLike) {
        this.networkNameLike = networkNameLike;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public String getBaseUserId() {
        return baseUserId;
    }

    @Override
    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }
}
