package com.skel.appskeletonv4.data.localdb.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "UsersTable")
public class UserEntity {


    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private int userId;

    @SerializedName("name")
    String name;

    @SerializedName("age")
    int age;

    @SerializedName("token")
    String token;

    @SerializedName("error")
    String error;

   /* public UserEntity(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }*/

    public UserEntity(int userId, String name, int age, String token, String error) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.token = token;
        this.error = error;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
