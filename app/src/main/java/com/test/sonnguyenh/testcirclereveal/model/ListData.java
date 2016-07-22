package com.test.sonnguyenh.testcirclereveal.model;

import java.io.Serializable;

/**
 * Created by sonnguyenh on 7/22/2016.
 */
public class ListData implements Serializable{
    private String name;
    private String imgUrl;

    public ListData(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
