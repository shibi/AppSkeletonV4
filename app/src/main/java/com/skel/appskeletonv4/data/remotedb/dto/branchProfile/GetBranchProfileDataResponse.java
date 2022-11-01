
package com.skel.appskeletonv4.data.remotedb.dto.branchProfile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetBranchProfileDataResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private BranchProfileData data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public BranchProfileData getData() {
        return data;
    }

    public void setData(BranchProfileData  data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
