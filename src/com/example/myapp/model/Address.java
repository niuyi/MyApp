package com.example.myapp.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by linniu on 2015/10/31.
 */
public class Address extends DataSupport{

    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String value = null;

    @Column(nullable = false)
    private String timestamp = null;

    @Column(nullable = false, defaultValue = "test")
    private String orderId = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
