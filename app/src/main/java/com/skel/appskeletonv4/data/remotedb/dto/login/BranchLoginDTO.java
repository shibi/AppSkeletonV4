
package com.skel.appskeletonv4.data.remotedb.dto.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BranchLoginDTO {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Branch data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Branch getData() {
        return data;
    }

    public void setData(Branch data) {
        this.data = data;
    }

}
