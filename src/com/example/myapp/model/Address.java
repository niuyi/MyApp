package com.example.myapp.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by linniu on 2015/10/31.
 */
public class Address extends DataSupport{

    private int id;

    @Column(nullable = false, unique = false)
    private String value = null;

    @Column(nullable = false)
    private String timestamp = null;

    @Column(nullable = false, defaultValue = "test")
    private String orderId = null;

    @Column(ignore = true)
    private String test;

    @Column(nullable = true, defaultValue = "test2")
    private String test2;

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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }
}
