package com.logibeat.cloud.common.enumtype;

public enum MonitorDistance{
    OutPoint(500), InPoint(500), InEndPoint(2000);

    private int value;

    private MonitorDistance(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}