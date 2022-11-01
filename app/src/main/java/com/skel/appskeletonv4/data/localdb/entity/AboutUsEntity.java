package com.skel.appskeletonv4.data.localdb.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "aboutus_table")
public class AboutUsEntity {

    @PrimaryKey
    @NonNull
    @SerializedName("messsage")
    private String data;

    public AboutUsEntity(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
